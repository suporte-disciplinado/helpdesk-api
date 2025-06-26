package com.suportedisciplinado.api.controller;

import com.suportedisciplinado.api.model.Ticket;
import com.suportedisciplinado.api.service.TicketService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> createTicket(@RequestBody Ticket newTicket)
    {
        return ticketService.createTicket(newTicket);
    }

    @PutMapping
    public ResponseEntity<String>  updateTicket(@RequestBody Ticket updatedTicket)
    {
        return ticketService.updateTicket(updatedTicket);
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> searchForAllTickets()
    {
        return ticketService.getAllTickets();
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<Ticket> searchForTicket (@PathVariable Long ticketId)
    {
        return ticketService.getTicketById(ticketId);
    }

    @DeleteMapping("/{ticketId}")
    public ResponseEntity<String>  deleteTicket(@PathVariable Long ticketId)
    {
        return ticketService.deleteTicketById(ticketId);
    }
}
