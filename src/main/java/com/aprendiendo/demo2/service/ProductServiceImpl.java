package com.aprendiendo.demo2.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

import com.aprendiendo.demo2.model.Product;
import com.aprendiendo.demo2.model.ProductDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductServiceBL {

    private final DynamoDBMapper dynamoDBMapper;

    public ProductServiceImpl(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        List<Product> products = dynamoDBMapper.scan(Product.class, scanExpression);
        return products.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(String id) {
        Product product = dynamoDBMapper.load(Product.class, id);
        return convertToDTO(product);
    }

    @Override
    public ProductDto createNewProduct(ProductDto dto) {
        Product product = new Product();
        BeanUtils.copyProperties(dto, product);

        dynamoDBMapper.save(product);

        return convertToDTO(product);
    }

    @Override
    public ProductDto updateProduct(String id, ProductDto dto) {
        Product product = dynamoDBMapper.load(Product.class, id);
        BeanUtils.copyProperties(dto, product);

        dynamoDBMapper.save(product);

        return convertToDTO(product);
    }

    @Override
    public void deleteProduct(String id) {
        Product product = dynamoDBMapper.load(Product.class, id);
        if (product != null) {
            dynamoDBMapper.delete(product);
        }
    }

    private ProductDto convertToDTO(Product product) {
        ProductDto dto = new ProductDto();
        BeanUtils.copyProperties(product, dto);
        return dto;
    }
}