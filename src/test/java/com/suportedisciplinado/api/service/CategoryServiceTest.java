package com.suportedisciplinado.api.service;

import com.suportedisciplinado.api.model.Category;
import com.suportedisciplinado.api.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    private Category sampleCategory;

    @BeforeEach
    void setup() {
        sampleCategory = new Category();
        sampleCategory.setId(1L);
        sampleCategory.setName("Infraestrutura");
        sampleCategory.setDescription("Categoria de testes");
    }

    @Test
    void testFindAll() {
        when(categoryRepository.findAll()).thenReturn(List.of(sampleCategory));

        List<Category> categories = categoryService.findAll();

        assertEquals(1, categories.size());
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    void testGetAllCategories() {
        when(categoryRepository.findAll()).thenReturn(List.of(sampleCategory));

        ResponseEntity<List<Category>> response = categoryService.getAllCategories();

        assertEquals(200, response.getStatusCodeValue());
        assertFalse(response.getBody().isEmpty());
    }

    @Test
    void testGetCategoryById_Success() {
        when(categoryRepository.getOne(1L)).thenReturn(sampleCategory);

        ResponseEntity<Category> response = categoryService.getCategoryById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Infraestrutura", response.getBody().getName());
    }

    @Test
    void testGetCategoryById_NullId() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            categoryService.getCategoryById(null);
        });

        assertEquals("The category id informed is null, please pass a valid id!", exception.getMessage());
    }

    @Test
    void testCreateCategory_Success() {
        when(categoryRepository.saveAndFlush(sampleCategory)).thenReturn(sampleCategory);

        ResponseEntity<String> response = categoryService.createCategory(sampleCategory);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Category created successfully!", response.getBody());
    }

    @Test
    void testCreateCategory_Null() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            categoryService.createCategory(null);
        });

        assertEquals("The category informed is null, please pass a valid category!", exception.getMessage());
    }

    @Test
    void testUpdateCategory_Success() {
        when(categoryRepository.getOne(1L)).thenReturn(sampleCategory);
        when(categoryRepository.save(any(Category.class))).thenReturn(sampleCategory);

        sampleCategory.setName("Atualizada");
        ResponseEntity<String> response = categoryService.updateCategory(sampleCategory);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Category updated successfully!", response.getBody());
    }

    @Test
    void testUpdateCategory_Null() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            categoryService.updateCategory(null);
        });

        assertEquals("The category informed is null, please pass a valid category!", exception.getMessage());
    }

    @Test
    void testDeleteCategoryById() {
        doNothing().when(categoryRepository).deleteById(1L);

        ResponseEntity<String> response = categoryService.deleteCategoryById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Category deleted successfully!", response.getBody());
    }
}