package com.softpedia.DemoShoppingCart.services.product;

import com.softpedia.DemoShoppingCart.exception.ProductNotFoundException;
import com.softpedia.DemoShoppingCart.models.Product;
import com.softpedia.DemoShoppingCart.repos.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor

public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Override
    @Transactional
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findProductById(Long prodId) {
        return productRepository.findById(prodId).orElseThrow(
                () -> new ProductNotFoundException("Product not found!")
        );
    }

    @Override
    @Transactional
    public void updateProductById(Product product, Long prodId) {
        Product existingProduct = findProductById(prodId);
        existingProduct.setName(product.getName());
        existingProduct.setBrand(product.getBrand());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setInventory(product.getInventory());
        productRepository.save(existingProduct);
    }

    @Override
    @Transactional
    public void deleteProductById(Long prodId) {
        productRepository.findById(prodId).ifPresentOrElse(productRepository::delete,
                ()-> {throw new ProductNotFoundException("Product not found to delete!");});
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryNameAndBrand(category,brand);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> findByBrand(String brand) {
        return productRepository.findByBrand(brand);
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
