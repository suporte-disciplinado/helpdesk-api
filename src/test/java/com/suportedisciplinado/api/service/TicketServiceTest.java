package com.suportedisciplinado.api.service;

import com.suportedisciplinado.api.model.Category;
import com.suportedisciplinado.api.model.Ticket;
import com.suportedisciplinado.api.model.User;
import com.suportedisciplinado.api.repository.CategoryRepository;
import com.suportedisciplinado.api.repository.TicketRepository;
import com.suportedisciplinado.api.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TicketServiceTest {

    private TicketService ticketService;
    private TicketRepository ticketRepository;
    private UserRepository userRepository;
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        ticketRepository = mock(TicketRepository.class);
        userRepository = mock(UserRepository.class);
        categoryRepository = mock(CategoryRepository.class);
        ticketService = new TicketService(ticketRepository, userRepository, categoryRepository);
    }

    @Test
    void testGetAllTickets() {
        Ticket ticket = new Ticket();
        when(ticketRepository.findAll()).thenReturn(List.of(ticket));

        ResponseEntity<List<Ticket>> response = ticketService.getAllTickets();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testGetTicketById() {
        Ticket ticket = new Ticket();
        ticket.setId(1L);
        when(ticketRepository.getOne(1L)).thenReturn(ticket);

        ResponseEntity<Ticket> response = ticketService.getTicketById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    void testCreateTicket() {
        User user = new User();
        user.setId(1L);

        Category category = new Category();
        category.setId(2L);

        Ticket ticket = new Ticket();
        ticket.setTitle("Novo ticket");
        ticket.setAssignedAgent(user);
        ticket.setUser(user);
        ticket.setCategory(category);

        when(userRepository.getOne(1L)).thenReturn(user);
        when(categoryRepository.getOne(2L)).thenReturn(category);
        when(ticketRepository.saveAndFlush(any(Ticket.class))).thenReturn(ticket);

        ResponseEntity<String> response = ticketService.createTicket(ticket);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Ticket created successfully!", response.getBody());
    }

    @Test
    void testUpdateTicket() {
        User user = new User();
        user.setId(1L);

        Category category = new Category();
        category.setId(2L);

        Ticket existingTicket = new Ticket();
        existingTicket.setId(1L);
        existingTicket.setUser(user);
        existingTicket.setAssignedAgent(user);
        existingTicket.setCategory(category);

        Ticket updatedTicket = new Ticket();
        updatedTicket.setId(1L);
        updatedTicket.setTitle("Atualizado");
        updatedTicket.setUser(user);
        updatedTicket.setAssignedAgent(user);
        updatedTicket.setCategory(category);

        when(ticketRepository.getOne(1L)).thenReturn(existingTicket);
        when(userRepository.getOne(1L)).thenReturn(user);
        when(categoryRepository.getOne(2L)).thenReturn(category);
        when(ticketRepository.save(any(Ticket.class))).thenReturn(updatedTicket);

        ResponseEntity<String> response = ticketService.updateTicket(updatedTicket);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Ticket updated successfully!", response.getBody());
    }

    @Test
    void testDeleteTicketById() {
        doNothing().when(ticketRepository).deleteById(1L);

        ResponseEntity<String> response = ticketService.deleteTicketById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Ticket deleted successfully!", response.getBody());
    }
}

