package com.aprendiendo.demo2.model;

import lombok.Data;

@Data
public class ProductDto {

    private String id;
    private String name;
    private double price;
    private Long stockCount;

}