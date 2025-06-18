package com.suportedisciplinado.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suportedisciplinado.api.config.SecurityConfigTest;
import com.suportedisciplinado.api.model.Feedback;
import com.suportedisciplinado.api.service.FeedbackService;
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

import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(SecurityConfigTest.class)
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = FeedbackController.class)
public class FeedbackControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private FeedbackService feedbackService;

  @Test
  @WithMockUser(username = "betofrassoncb@gmail.com", roles = {"USER"}, password = "1234")
  void shouldReturnAllFeedbacks() throws Exception {
    when(feedbackService.getAllFeedbacks()).thenReturn((ResponseEntity.ok(List.of(new Feedback()))));

    mockMvc.perform(get("/api/feedback")
           .contentType(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk());
  }

  @Test
  @WithMockUser
  void shouldReturnFeedbackById() throws Exception{
    Feedback feedback = new Feedback();
    feedback.setId(1L);
    when(feedbackService.getFeedbackById(1L)).thenReturn(ResponseEntity.ok(feedback));

    mockMvc.perform(get("/api/feedback/1")
           .contentType(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk());
  }

  @Test
  @WithMockUser
    void shouldCreateFeedback() throws Exception {
        Feedback feedback = new Feedback();
        feedback.setRating(5);
        feedback.setFeedbackAt(new Date());

        when(feedbackService.createFeedback(any(Feedback.class))).thenReturn(ResponseEntity.ok(feedback));

        mockMvc.perform(post("/api/feedback")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(feedback)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldUpdateFeedback() throws Exception {
        Feedback feedback = new Feedback();
        feedback.setId(1L);
        feedback.setRating(4);

        when(feedbackService.getFeedbackById(1L)).thenReturn(ResponseEntity.ok(feedback));
        when(feedbackService.createFeedback(any(Feedback.class))).thenReturn(ResponseEntity.ok(feedback));

        mockMvc.perform(put("/api/feedback/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(feedback)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldDeleteFeedback() throws Exception {
        mockMvc.perform(delete("/api/feedback/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

}
