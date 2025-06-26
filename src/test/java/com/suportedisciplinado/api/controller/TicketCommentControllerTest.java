package com.suportedisciplinado.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suportedisciplinado.api.config.SecurityConfigTest;
import com.suportedisciplinado.api.model.Ticket;
import com.suportedisciplinado.api.model.TicketComment;
import com.suportedisciplinado.api.model.User;
import com.suportedisciplinado.api.service.TicketCommentService;
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
@WebMvcTest(controllers = TicketCommentController.class)
public class TicketCommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TicketCommentService ticketCommentService;

    @Test
    @WithMockUser
    void shouldReturnAllComments() throws Exception {
        when(ticketCommentService.getAllComments())
                .thenReturn(ResponseEntity.ok(List.of(new TicketComment())));

        mockMvc.perform(get("/api/comment")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldReturnCommentById() throws Exception {
        TicketComment comment = new TicketComment();
        comment.setId(1L);

        when(ticketCommentService.getCommentById(1L)).thenReturn(ResponseEntity.ok(comment));

        mockMvc.perform(get("/api/comment/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldCreateComment() throws Exception {
        TicketComment comment = new TicketComment();
        comment.setComment("Comentário Teste");
        comment.setTicket(new Ticket());
        comment.setUser(new User());

        when(ticketCommentService.createComment(any(TicketComment.class)))
                .thenReturn(ResponseEntity.ok("Comment created successfully!"));

        mockMvc.perform(post("/api/comment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comment)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldUpdateComment() throws Exception {
        TicketComment comment = new TicketComment();
        comment.setId(1L);
        comment.setComment("Comentário Atualizado");
        comment.setTicket(new Ticket());
        comment.setUser(new User());

        when(ticketCommentService.updateComment(any(TicketComment.class)))
                .thenReturn(ResponseEntity.ok("Comment updated successfully!"));

        mockMvc.perform(put("/api/comment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comment)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldDeleteComment() throws Exception {
        when(ticketCommentService.deleteCommentById(1L))
                .thenReturn(ResponseEntity.ok("Comment deleted successfully!"));

        mockMvc.perform(delete("/api/comment/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
