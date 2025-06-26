package com.suportedisciplinado.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suportedisciplinado.api.config.SecurityConfigTest;
import com.suportedisciplinado.api.model.KnowledgeBaseTag;
import com.suportedisciplinado.api.service.KnowledgeBaseTagService;
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
@WebMvcTest(controllers = KnowledgeBaseTagController.class)
public class KnowledgeBaseTagControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private KnowledgeBaseTagService knowledgeBaseTagService;

  @Test
  @WithMockUser
  void shouldReturnAllTags() throws Exception {
    when(knowledgeBaseTagService.getAllKnowledgeBaseTags())
                                .thenReturn(ResponseEntity.ok(List.of(new KnowledgeBaseTag(1L, "Tag1"))));

    mockMvc.perform(get("/api/knowledge-base/tag")
           .contentType(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk());
  }

  @Test
  @WithMockUser
  void shouldReturnTagById() throws Exception {
    when(knowledgeBaseTagService.getKnowledgeBaseTagById(1L))
                                .thenReturn(ResponseEntity.ok(Optional.of(new KnowledgeBaseTag(1L, "Tag1"))));

    mockMvc.perform(get("/api/knowledge-base/tag/1")
           .contentType(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk());
  }

  @Test
    @WithMockUser
    void shouldCreateTag() throws Exception {
        KnowledgeBaseTag tag = new KnowledgeBaseTag(null, "Nova Tag");
        KnowledgeBaseTag savedTag = new KnowledgeBaseTag(1L, "Nova Tag");

        when(knowledgeBaseTagService.createKnowledgeBaseTag(any(KnowledgeBaseTag.class)))
                .thenReturn(ResponseEntity.ok(savedTag));

        mockMvc.perform(post("/api/knowledge-base/tag")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tag)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldUpdateTag() throws Exception {
        KnowledgeBaseTag tag = new KnowledgeBaseTag(1L, "Atualizada");

        when(knowledgeBaseTagService.updateKnowledgeBaseTag(any(KnowledgeBaseTag.class)))
                .thenReturn(ResponseEntity.ok(tag));

        mockMvc.perform(put("/api/knowledge-base/tag")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tag)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldDeleteTag() throws Exception {
        when(knowledgeBaseTagService.deleteKnowledgeBaseTag(1L))
                .thenReturn(ResponseEntity.ok("Tag deleted successfully!"));

        mockMvc.perform(delete("/api/knowledge-base/tag/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
