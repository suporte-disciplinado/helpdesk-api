package com.suportedisciplinado.api.service;

import com.suportedisciplinado.api.model.Category;
import com.suportedisciplinado.api.model.Role;
import com.suportedisciplinado.api.model.Ticket;
import com.suportedisciplinado.api.model.User;
import com.suportedisciplinado.api.repository.CategoryRepository;
import com.suportedisciplinado.api.repository.TicketRepository;
import com.suportedisciplinado.api.repository.UserRepository;
import com.suportedisciplinado.api.security.CustomUserDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        User adminUser = new User();
        adminUser.setRole(Role.ADMIN);

        CustomUserDetails userDetails = new CustomUserDetails(adminUser);

        Ticket ticket = new Ticket();
        when(ticketRepository.searchTickets(null, null))
                .thenReturn(List.of(ticket));

        ResponseEntity<List<Ticket>> response = ticketService.getAllTickets(userDetails, null, null);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testGetTicketById() {
        Ticket ticket = new Ticket();
        ticket.setId(1L);
        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));

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

        when(ticketRepository.findById(1L)).thenReturn(Optional.of(existingTicket));
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

