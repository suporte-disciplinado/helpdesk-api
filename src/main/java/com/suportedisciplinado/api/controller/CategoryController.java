package com.suportedisciplinado.api.controller;

import com.suportedisciplinado.api.model.Category;
import com.suportedisciplinado.api.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController
{
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService)
    {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<String> createCategory(@RequestBody Category newCategory)
    {
        return categoryService.createCategory(newCategory);
    }

    @PutMapping
    public ResponseEntity<String> updateCategory(@RequestBody Category updatedCategory)
    {
        return categoryService.updateCategory(updatedCategory);
    }

    @GetMapping
    public ResponseEntity<List<Category>> searchForAllCategories()
    {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> searchForCategory (@PathVariable Long categoryId)
    {
        return categoryService.getCategoryById(categoryId);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId)
    {
        return categoryService.deleteCategoryById(categoryId);
    }
}
