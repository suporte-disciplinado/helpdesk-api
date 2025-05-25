package com.suportedisciplinado.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suportedisciplinado.api.config.SecurityConfig;
import com.suportedisciplinado.api.model.Feedback;
import com.suportedisciplinado.api.repository.FeedbackRepository;
import com.suportedisciplinado.api.repository.TicketRepository;
import com.suportedisciplinado.api.repository.UserRepository;
import com.suportedisciplinado.api.service.FeedbackService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@Import(SecurityConfig.class)
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = FeedbackController.class)
public class FeedbackControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private FeedbackService feedbackService;

  @MockBean
    private FeedbackRepository feedbackRepository;

  @MockBean
  private UserRepository userRepository;

  @MockBean
  private TicketRepository ticketRepository;

  @Test
  @WithMockUser(username = "betofrassoncb@gmail.com", roles = {"USER"}, password = "1234")
  void shouldReturnAllFeedbacks() throws Exception {
    when(feedbackService.findAll()).thenReturn(List.of(new Feedback()));

    mockMvc.perform(get("/api/feedback")
           .contentType(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk());
  }

  @Test
  @WithMockUser
  void shouldReturnFeedbackById() throws Exception{
    Feedback feedback = new Feedback();
    feedback.setId(1L);
    when(feedbackService.findById(1L)).thenReturn(Optional.of(feedback));

    mockMvc.perform(get("/api/feedback/1")
           .contentType(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk());
  }

}
