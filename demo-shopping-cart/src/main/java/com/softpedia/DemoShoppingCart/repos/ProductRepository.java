package com.softpedia.DemoShoppingCart.repos;

import com.softpedia.DemoShoppingCart.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
