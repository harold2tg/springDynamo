package com.aprendiendo.demo2.controller;

import com.aprendiendo.demo2.model.ProductDto;
import com.aprendiendo.demo2.service.ProductServiceBL;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductServiceBL productServiceBL;

    public ProductController(ProductServiceBL productServiceBL) {
        this.productServiceBL = productServiceBL;
    }

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productServiceBL.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable String id) {
        return productServiceBL.getProductById(id);
    }

    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto productDTO) {
        return productServiceBL.createNewProduct(productDTO);
    }

    @PutMapping("/{id}")
    public ProductDto updateProduct(@PathVariable String id, @RequestBody ProductDto productDTO) {
        return productServiceBL.updateProduct(id, productDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id) {
        productServiceBL.deleteProduct(id);
    }


}