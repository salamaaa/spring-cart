package com.softpedia.DemoShoppingCart.dto;

import com.softpedia.DemoShoppingCart.models.Product;
import lombok.Data;

import java.sql.Blob;

@Data
public class ImageDto {
    private Long id;
    private String imageName;
    private String imageType;
    private Blob image;
    private String downloadUrl;
    private Product product;
}
