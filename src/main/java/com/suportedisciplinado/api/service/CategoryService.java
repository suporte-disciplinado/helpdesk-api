package com.suportedisciplinado.api.service;

import com.suportedisciplinado.api.model.Category;
import com.suportedisciplinado.api.model.Ticket;
import com.suportedisciplinado.api.repository.CategoryRepository;
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

    public Category getCategoryById(Long categoryId)
    throws NullPointerException
    {
        Objects.requireNonNull(categoryId, "O id da categoria informada está nulo, por favor informe um id válido!");

        Category category = categoryRepository.getOne(categoryId);
        validateCategory(category);
        return category;
    }

    public void updateCategory(Category updatedCategory)
    throws NullPointerException
    {
        validateCategory(updatedCategory);
        Category categoryToUpdate = getCategoryById(updatedCategory.getId());

        categoryToUpdate.setName(updatedCategory.getName());
        categoryToUpdate.setDescription(updatedCategory.getDescription());

        categoryRepository.save(categoryToUpdate);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }

    public void createCategory(Category newCategory) {
        validateCategory(newCategory);
        categoryRepository.saveAndFlush(newCategory);
    }

    private void validateCategory(Category category)
    throws NullPointerException
    {
        Objects.requireNonNull(category, "A categoria não pode ser nula, por favor forneca uma categoria válida!");
    }
}
