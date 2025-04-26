package com.softpedia.DemoShoppingCart.repos;

import com.softpedia.DemoShoppingCart.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByCategoryName(String category);

    List<Product> findByName(String name);

    List<Product> findByBrand(String brand);
}
