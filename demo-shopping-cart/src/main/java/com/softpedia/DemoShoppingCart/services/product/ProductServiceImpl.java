package com.softpedia.DemoShoppingCart.services.product;

import com.softpedia.DemoShoppingCart.dto.ProductDto;
import com.softpedia.DemoShoppingCart.exception.ProductNotFoundException;
import com.softpedia.DemoShoppingCart.models.Category;
import com.softpedia.DemoShoppingCart.models.Product;
import com.softpedia.DemoShoppingCart.repos.CategoryRepository;
import com.softpedia.DemoShoppingCart.repos.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public Product createProduct(ProductDto productDto, Category category) {
        return new Product(
                productDto.getName(),
                productDto.getBrand(),
                productDto.getPrice(),
                productDto.getInventory(),
                productDto.getDescription(),
                category
        );
    }
    @Override
    @Transactional
    public Product addProduct(ProductDto productDto) {
        Category category = Optional.ofNullable(categoryRepository.findByName(productDto.getCategory().getName()))
                .orElseGet( () -> {
                            Category newCategory = new Category(productDto.getCategory().getName());
                            return categoryRepository.save(newCategory);
                        });
        productDto.setCategory(category);
        return productRepository.save(createProduct(productDto,category));
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
        return productRepository.findByBrandAndName(brand,name);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }
}
