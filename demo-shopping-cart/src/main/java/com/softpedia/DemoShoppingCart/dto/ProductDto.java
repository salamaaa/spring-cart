package com.softpedia.DemoShoppingCart.dto;

import com.softpedia.DemoShoppingCart.models.Category;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private String description;
    private Category category;
}