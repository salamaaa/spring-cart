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


    //helper method to be used in the addProduct() method
    @Override
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
    public Product updateProduct(ProductDto productDto, Long prodId) {
        return productRepository.findById(prodId)
                .map(existingProduct -> updateExistingProduct(existingProduct,productDto))
                .map(productRepository::save)
                .orElseThrow(()-> new ProductNotFoundException("Product not Found!"));
    }

    //helper update method to be used in the updateProduct() method
    @Override
    public Product updateExistingProduct(Product existingProduct, ProductDto productDto) {
        existingProduct.setName(productDto.getName());
        existingProduct.setBrand(productDto.getBrand());
        existingProduct.setDescription(productDto.getDescription());
        existingProduct.setPrice(productDto.getPrice());
        existingProduct.setInventory(productDto.getInventory());

        Category category = categoryRepository.findByName(productDto.getCategory().getName());
        existingProduct.setCategory(category);

        return existingProduct;
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
