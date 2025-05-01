package com.softpedia.DemoShoppingCart.services.category;

import com.softpedia.DemoShoppingCart.exception.CategoryNotFoundException;
import com.softpedia.DemoShoppingCart.models.Category;
import com.softpedia.DemoShoppingCart.repos.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(
                ()-> new CategoryNotFoundException("Category not found")
        );
    }

    @Override
    public Category findByName(String name) {
        return null;
    }

    @Override
    public List<Category> getAllCategories() {
        return List.of();
    }

    @Override
    public Category addCategory(Category category) {
        return null;
    }

    @Override
    public Category updateCategory(Category category, Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
