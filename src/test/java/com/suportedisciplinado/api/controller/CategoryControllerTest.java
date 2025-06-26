package com.suportedisciplinado.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suportedisciplinado.api.config.SecurityConfigTest;
import com.suportedisciplinado.api.model.Category;
import com.suportedisciplinado.api.service.CategoryService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(SecurityConfigTest.class)
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = CategoryController.class)
public class CategoryControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private CategoryService categoryService;

  @Test
  @WithMockUser
  void shouldReturnAllCategories() throws Exception {
    when(categoryService.getAllCategories()).thenReturn(ResponseEntity.ok(List.of(new Category())));

    mockMvc.perform(get("/api/category")
           .contentType(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk());
  }

  @Test
  @WithMockUser
  void shouldCreateCategory() throws Exception {
    Category category = new Category();
    category.setName("Nova Categoria");
    category.setDescription("Descrição da Categoria");

    when(categoryService.createCategory(any(Category.class)))
                        .thenReturn(ResponseEntity.ok("Category created successfully!"));

    mockMvc.perform(post("/api/category").contentType(MediaType.APPLICATION_JSON)
                                         .content(objectMapper.writeValueAsString(category))).andExpect(status().isOk());
  }

  @Test
  @WithMockUser
  void shouldUpdateCategory() throws Exception {
    Category category = new Category();
    category.setId(1L);
    category.setName("Atualizado");
    category.setDescription("Descrição atualizada");

    when(categoryService.updateCategory(any(Category.class)))
                        .thenReturn(ResponseEntity.ok("Category updated successfully!"));

    mockMvc.perform(put("/api/category").contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(category)))
                                        .andExpect(status().isOk());
  }

  @Test
  @WithMockUser
  void shouldDeleteCategory() throws Exception {

    when(categoryService.deleteCategoryById(1L)).thenReturn(ResponseEntity.ok("Category deleted successfully!"));

    mockMvc.perform(delete("/api/category/1").contentType(MediaType.APPLICATION_JSON))
                                             .andExpect(status().isOk());
  }

}
