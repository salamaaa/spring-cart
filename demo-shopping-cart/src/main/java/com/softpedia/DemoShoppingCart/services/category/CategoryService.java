package com.softpedia.DemoShoppingCart.services.category;

import com.softpedia.DemoShoppingCart.models.Category;

import java.util.List;

public interface CategoryService {
    Category findById(Long id);
    Category findByName(String name);
    List<Category> getAllCategories();
    Category addCategory(Category category);
    Category updateCategory(Category category,Long id);
    void deleteById(Long id);
}
