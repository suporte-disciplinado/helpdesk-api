package com.suportedisciplinado.api.service;

import com.suportedisciplinado.api.model.Category;
import com.suportedisciplinado.api.repository.CategoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository)
    {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public ResponseEntity<Category> getCategoryById(Long categoryId)
    throws NullPointerException
    {
        Objects.requireNonNull(categoryId, "The category id informed is null, please pass a valid id!");

        Category category = categoryRepository.getOne(categoryId);
        validateCategory(category);
        return ResponseEntity.ok(category);
    }

    public ResponseEntity<String> updateCategory(Category updatedCategory)
    throws NullPointerException
    {
        validateCategory(updatedCategory);
        Category categoryToUpdate = getCategoryById(updatedCategory.getId()).getBody();

        categoryToUpdate.setName(updatedCategory.getName());
        categoryToUpdate.setDescription(updatedCategory.getDescription());

        categoryRepository.save(categoryToUpdate);
        return ResponseEntity.ok("Category updated successfully!");
    }

    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryRepository.findAll());
    }

    public ResponseEntity<String> deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
        return ResponseEntity.ok("Category deleted successfully!");
    }

    public ResponseEntity<String> createCategory(Category newCategory) {
        validateCategory(newCategory);
        categoryRepository.saveAndFlush(newCategory);
        return ResponseEntity.ok("Category created successfully!");
    }

    private void validateCategory(Category category)
    throws NullPointerException
    {
        Objects.requireNonNull(category, "The category informed is null, please pass a valid category!");
    }
}
