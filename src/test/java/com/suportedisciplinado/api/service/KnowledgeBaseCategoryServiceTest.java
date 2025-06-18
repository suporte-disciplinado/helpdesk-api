package com.suportedisciplinado.api.service;

import com.suportedisciplinado.api.model.KnowledgeBaseCategory;
import com.suportedisciplinado.api.repository.KnowledgeBaseCategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class KnowledgeBaseCategoryServiceTest {

    @Mock
    private KnowledgeBaseCategoryRepository repository;

    @InjectMocks
    private KnowledgeBaseCategoryService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnAllCategories() {
        when(repository.findAll()).thenReturn(Arrays.asList(new KnowledgeBaseCategory()));

        ResponseEntity<List<KnowledgeBaseCategory>> response = service.getAllKnowledgeBaseCategories();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void shouldReturnCategoryById() {
        KnowledgeBaseCategory category = new KnowledgeBaseCategory();
        category.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(category));

        ResponseEntity<Optional<KnowledgeBaseCategory>> response = service.getKnowledgeBaseCategoryById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().isPresent());
        assertEquals(1L, response.getBody().get().getId());
    }

    @Test
    void shouldCreateCategory() {
        KnowledgeBaseCategory category = new KnowledgeBaseCategory();
        category.setDescription("Docs");
        when(repository.saveAndFlush(any(KnowledgeBaseCategory.class))).thenReturn(category);

        ResponseEntity<KnowledgeBaseCategory> response = service.createKnowledgeBaseCategory(category);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Docs", response.getBody().getDescription());
    }

    @Test
    void shouldUpdateCategory() {
        KnowledgeBaseCategory category = new KnowledgeBaseCategory(1L, "New Category");

        when(repository.findById(1L)).thenReturn(Optional.of(category));
        when(repository.saveAndFlush(any(KnowledgeBaseCategory.class))).thenReturn(category);

        ResponseEntity<KnowledgeBaseCategory> response = service.updateKnowledgeBaseCategory(category);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("New Category", response.getBody().getDescription());
    }

    @Test
    void shouldDeleteCategory() {
        doNothing().when(repository).deleteById(1L);

        ResponseEntity<String> response = service.deleteKnowledgeBaseCategory(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Category deleted successfully!", response.getBody());
    }
}

