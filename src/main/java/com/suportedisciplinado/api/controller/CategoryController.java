package com.suportedisciplinado.api.controller;

import com.suportedisciplinado.api.model.Category;
import com.suportedisciplinado.api.service.CategoryService;
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

    @PostMapping("/register/category")
    public void createCategory(@RequestBody Category newCategory)
    {
        categoryService.createCategory(newCategory);
    }

    @PostMapping("/update/category")
    public void updateCategory(@RequestBody Category updatedCategory)
    {
        categoryService.updateCategory(updatedCategory);
    }

    @GetMapping("/search/categories")
    public List<Category> searchForAllCategories()
    {
        return categoryService.getAllCategories();
    }

    @GetMapping("/search/category/{categoryId}")
    public @ResponseBody Category searchForCategory (@PathVariable Long categoryId)
    {
        return categoryService.getCategoryById(categoryId);
    }

    @DeleteMapping("/delete/category/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId)
    {
        categoryService.deleteCategoryById(categoryId);
    }
}
