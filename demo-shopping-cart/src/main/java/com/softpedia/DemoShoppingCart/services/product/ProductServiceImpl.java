package com.softpedia.DemoShoppingCart.services.product;

import com.softpedia.DemoShoppingCart.models.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class ProductServiceImpl implements ProductService{

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product findProductById(Long prodId) {
        return null;
    }

    @Override
    public void updateProductById(Product product, Long prodId) {

    }

    @Override
    public void deleteProductById(Long prodId) {

    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return List.of();
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return List.of();
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return List.of();
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name) {
        return List.of();
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return 0L;
    }
}
