package com.suportedisciplinado.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suportedisciplinado.api.config.SecurityConfigTest;
import com.suportedisciplinado.api.model.Role;
import com.suportedisciplinado.api.model.Ticket;
import com.suportedisciplinado.api.model.User;
import com.suportedisciplinado.api.security.CustomUserDetails;
import com.suportedisciplinado.api.service.TicketService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
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
    @WithMockUser(username = "admin@gmail.com", roles = {"ADMIN"}, password = "1234")
    void shouldReturnAllTicketsForAdmin() throws Exception {
        when(service.getAllTickets(any(CustomUserDetails.class), isNull(), isNull()))
                .thenReturn(ResponseEntity.ok(List.of(new Ticket())));

        mockMvc.perform(get("/api/ticket")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "betofrassoncb@gmail.com", roles = {"USER"}, password = "1234")
    void shouldReturnAllTicketsForUser() throws Exception {
        when(service.getAllTickets(any(CustomUserDetails.class), isNull(), isNull()))
                .thenReturn(ResponseEntity.ok(List.of(new Ticket())));

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
        User user = new User();
        user.setId(1L);
        user.setEmail("admin@teste.com");

        CustomUserDetails userDetails = new CustomUserDetails(user);
        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(auth);

        Ticket ticket = new Ticket();
        ticket.setTitle("Test Ticket");
        ticket.setDescription("Test Description");
        ticket.setUser(user);
        ticket.setAssignedAgent(user);

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
