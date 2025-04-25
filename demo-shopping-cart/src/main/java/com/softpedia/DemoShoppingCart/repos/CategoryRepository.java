package com.softpedia.DemoShoppingCart.repos;

import com.softpedia.DemoShoppingCart.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
