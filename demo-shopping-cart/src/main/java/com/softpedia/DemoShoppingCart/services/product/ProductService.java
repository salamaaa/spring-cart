package com.softpedia.DemoShoppingCart.services.product;

import com.softpedia.DemoShoppingCart.dto.ProductDto;
import com.softpedia.DemoShoppingCart.models.Category;
import com.softpedia.DemoShoppingCart.models.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(ProductDto productDto, Category category);
    Product addProduct(ProductDto productDto);
    Product findProductById(Long prodId);
    void updateProductById(Product product,Long prodId);
    void deleteProductById(Long prodId);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByCategoryAndBrand(String category,String brand);
    List<Product> getProductsByName(String name);
    List<Product> findByBrand(String brand);
    List<Product> getProductsByBrandAndName(String brand,String name);
    Long countProductsByBrandAndName(String brand,String name);
}
