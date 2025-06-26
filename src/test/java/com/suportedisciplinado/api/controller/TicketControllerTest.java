package com.suportedisciplinado.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suportedisciplinado.api.config.SecurityConfigTest;
import com.suportedisciplinado.api.model.Ticket;
import com.suportedisciplinado.api.service.TicketService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Import(SecurityConfigTest.class)
@WebMvcTest(controllers = TicketController.class)
class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TicketService service;

    @Test
    @WithMockUser(username = "betofrassoncb@gmail.com", roles = {"USER"}, password = "1234")
    void shouldReturnAllTickets() throws Exception {
        when(service.getAllTickets()).thenReturn(ResponseEntity.ok(List.of(new Ticket())));

        mockMvc.perform(get("/api/ticket")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldReturnTicketById() throws Exception {
        Ticket ticket = new Ticket();
        ticket.setId(1L);
        when(service.getTicketById(1L)).thenReturn(ResponseEntity.ok(ticket));

        mockMvc.perform(get("/api/ticket/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldCreateTicket() throws Exception {
        Ticket ticket = new Ticket();
        ticket.setTitle("Test Ticket");
        ticket.setDescription("Test Description");

        when(service.createTicket(any(Ticket.class))).thenReturn(ResponseEntity.ok("Ticket created successfully!"));

        mockMvc.perform(post("/api/ticket")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ticket)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldUpdateTicket() throws Exception {
        Ticket ticket = new Ticket();
        ticket.setId(1L);
        ticket.setTitle("Updated Title");

        when(service.updateTicket(any(Ticket.class))).thenReturn(ResponseEntity.ok("Ticket updated successfully!"));

        mockMvc.perform(put("/api/ticket")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ticket)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldDeleteTicket() throws Exception {
        when(service.deleteTicketById(eq(1L))).thenReturn(ResponseEntity.ok("Ticket deleted successfully!"));

        mockMvc.perform(delete("/api/ticket/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
