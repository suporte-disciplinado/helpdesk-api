package com.suportedisciplinado.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suportedisciplinado.api.config.SecurityConfigTest;
import com.suportedisciplinado.api.model.KnowledgeBaseCategory;
import com.suportedisciplinado.api.service.KnowledgeBaseCategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(SecurityConfigTest.class)
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = KnowledgeBaseCategoryController.class)
public class KnowledgeBaseCategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private KnowledgeBaseCategoryService categoryService;

    @Test
    @WithMockUser
    void shouldReturnAllCategories() throws Exception {
        when(categoryService.getAllKnowledgeBaseCategories()).thenReturn(ResponseEntity.ok(List.of(new KnowledgeBaseCategory())));

        mockMvc.perform(get("/api/knowledge-base/category")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldReturnCategoryById() throws Exception {
        KnowledgeBaseCategory category = new KnowledgeBaseCategory();
        category.setId(1L);
        when(categoryService.getKnowledgeBaseCategoryById(1L)).thenReturn(ResponseEntity.ok(Optional.of(category)));

        mockMvc.perform(get("/api/knowledge-base/category/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldCreateCategory() throws Exception {
        KnowledgeBaseCategory category = new KnowledgeBaseCategory();
        category.setDescription("Docs");

        when(categoryService.createKnowledgeBaseCategory(any(KnowledgeBaseCategory.class))).thenReturn(ResponseEntity.ok(category));

        mockMvc.perform(post("/api/knowledge-base/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(category)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldUpdateCategory() throws Exception {
        KnowledgeBaseCategory category = new KnowledgeBaseCategory();
        category.setId(1L);
        category.setDescription("Updated Docs");

        when(categoryService.updateKnowledgeBaseCategory(any(KnowledgeBaseCategory.class))).thenReturn(ResponseEntity.ok(category));

        mockMvc.perform(put("/api/knowledge-base/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(category)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldDeleteCategory() throws Exception {
        when(categoryService.deleteKnowledgeBaseCategory(1L)).thenReturn(ResponseEntity.ok("Deleted successfully"));

        mockMvc.perform(delete("/api/knowledge-base/category/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
