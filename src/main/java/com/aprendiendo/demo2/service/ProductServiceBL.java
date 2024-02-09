package com.aprendiendo.demo2.service;


import com.aprendiendo.demo2.model.ProductDto;

import java.util.List;

public interface ProductServiceBL {
    List<ProductDto> getAllProducts();

    ProductDto getProductById(String id);

    ProductDto createNewProduct(ProductDto dto);

    ProductDto updateProduct(String id, ProductDto dto);

    void deleteProduct(String id);


}
