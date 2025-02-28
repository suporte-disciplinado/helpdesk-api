package com.suportedisciplinado.api.service;

import com.suportedisciplinado.api.model.Category;
import com.suportedisciplinado.api.model.Ticket;
import com.suportedisciplinado.api.repository.CategoryRepository;
import com.suportedisciplinado.api.repository.TicketRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TicketService
{
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public TicketService(
        TicketRepository ticketRepository,
        CategoryRepository categoryRepository,
        UserRepository userRepository
    )
    {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    public ResponseEntity<Ticket> getTicketById(Long ticketId)
    throws IllegalArgumentException, NullPointerException
    {
        Objects.requireNonNull(ticketId, "The ticket id informed is null, please pass a valid id!");

        Ticket ticket = ticketRepository.getOne(ticketId);
        validateTicket(ticket);
        return ResponseEntity.ok(ticket);
    }

    public ResponseEntity<String> updateTicket(Ticket updatedTicket)
    throws NullPointerException
    {
        validateTicket(updatedTicket);

        Ticket ticketToUpdate = getTicketById(updatedTicket.getId()).getBody();
        User user = userRepository.getOne(updatedTicket.getUser().getId());
        User assignedAgent = userRepository.getOne(updatedTicket.getAssignedAgent().getId());
        Category category = categoryRepository.getOne(updatedTicket.getCategory().getId());

        ticketToUpdate.setUser(user);
        ticketToUpdate.setAssignedAgent(assignedAgent);
        ticketToUpdate.setCategory(category);

        ticketToUpdate.setTitle(updatedTicket.getTitle());
        ticketToUpdate.setDescription(updatedTicket.getDescription());
        ticketToUpdate.setPriority(updatedTicket.getPriority());
        ticketToUpdate.setStatus(updatedTicket.getStatus());

        ticketRepository.save(ticketToUpdate);
        return ResponseEntity.ok("Ticket updated successfully!");
    }

    public ResponseEntity<List<Ticket>> getAllTickets() {
        return ResponseEntity.ok(ticketRepository.findAll());
    }

    public ResponseEntity<String> deleteTicketById(Long id) {
        ticketRepository.deleteById(id);
        return ResponseEntity.ok("Ticket deleted successfully!");
    }

    public ResponseEntity<String> createTicket(Ticket newTicket) {
        validateTicket(newTicket);

        User user = userRepository.getOne(newTicket.getUser().getId());
        User assignedAgent = userRepository.getOne(newTicket.getAssignedAgent().getId());
        Category category = categoryRepository.getOne(newTicket.getCategory().getId());

        newTicket.setUser(user);
        newTicket.setAssignedAgent(assignedAgent);
        newTicket.setCategory(category);

        ticketRepository.saveAndFlush(newTicket);
        return ResponseEntity.ok("Ticket created successfully!");
    }

    private void validateTicket(Ticket ticket)
    throws NullPointerException
    {
        Objects.requireNonNull(ticket, "The ticket received is null, please pass a valid ticket!");
    }
}
