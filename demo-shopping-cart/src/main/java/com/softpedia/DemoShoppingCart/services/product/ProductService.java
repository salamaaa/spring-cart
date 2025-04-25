package com.softpedia.DemoShoppingCart.services.product;

import com.softpedia.DemoShoppingCart.models.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    Product findProductById(Long prodId);
    void updateProductById(Product product,Long prodId);
    void deleteProductById(Long prodId);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByCategoryAndBrand(String category,String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByBrandAndName(String brand,String name);
    Long countProductsByBrandAndName(String brand,String name);
}
