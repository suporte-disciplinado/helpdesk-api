package com.suportedisciplinado.api.controller;

import com.suportedisciplinado.api.model.Status;
import com.suportedisciplinado.api.model.Ticket;
import com.suportedisciplinado.api.model.User;
import com.suportedisciplinado.api.security.CustomUserDetails;
import com.suportedisciplinado.api.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticket")
public class TicketController{
    private final TicketService ticketService;

    public TicketController(TicketService ticketService)
    {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<String> createTicket(@RequestBody Ticket newTicket, @AuthenticationPrincipal CustomUserDetails userDetails)
    {
        User user = userDetails.getUser();
        newTicket.setUser(user);

        return ticketService.createTicket(newTicket);
    }

    @PutMapping
    public ResponseEntity<String>  updateTicket(@RequestBody Ticket updatedTicket)
    {
        return ticketService.updateTicket(updatedTicket);
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> searchForAllTickets(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) String title) {

        return ticketService.getAllTickets(userDetails, status, title);
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<Ticket> searchForTicket (@PathVariable Long ticketId)
    {
        return ticketService.getTicketById(ticketId);
    }

    @DeleteMapping("/{ticketId}")
    public ResponseEntity<String> deleteTicket(@PathVariable Long ticketId)
    {
        return ticketService.deleteTicketById(ticketId);
    }
}
