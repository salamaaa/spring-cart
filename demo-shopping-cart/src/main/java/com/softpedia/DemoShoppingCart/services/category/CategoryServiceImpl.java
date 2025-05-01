package com.softpedia.DemoShoppingCart.services.category;

import com.softpedia.DemoShoppingCart.exception.CategoryAlreadyExistsException;
import com.softpedia.DemoShoppingCart.exception.CategoryNotFoundException;
import com.softpedia.DemoShoppingCart.models.Category;
import com.softpedia.DemoShoppingCart.repos.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        return categoryRepository.findByName(name);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    @Transactional
    public Category addCategory(Category category) {
        return Optional.of(category).filter(c ->
            !categoryRepository.existsByName(c.getName()))
                .map(categoryRepository::save)
                .orElseThrow(
                        ()-> new CategoryAlreadyExistsException(category.getName()+" already exists")
                );
    }

    @Override
    @Transactional
    public Category updateCategory(Category category, Long id) {
       return Optional.ofNullable(findById(id)).map(
               existingCategory-> {
                   existingCategory.setName(category.getName());
                   return categoryRepository.save(existingCategory);
               }
       ).orElseThrow(
               ()-> new CategoryNotFoundException("Category not found")
       );
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        categoryRepository.findById(id).ifPresentOrElse(
                categoryRepository::delete,
                ()->{
                    throw new CategoryNotFoundException("Category not found!");
                }
        );
    }
}