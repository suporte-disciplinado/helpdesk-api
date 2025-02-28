package com.suportedisciplinado.api.controller;

import com.suportedisciplinado.api.model.Ticket;
import com.suportedisciplinado.api.service.TicketService;
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

    @PostMapping("/register/ticket")
    public void createTicket(@RequestBody Ticket newTicket)
    {
        ticketService.createTicket(newTicket);
    }

    @PostMapping("/update/ticket")
    public void updateTicket(@RequestBody Ticket updatedTicket)
    {
        ticketService.updateTicket(updatedTicket);
    }

    @GetMapping("/search/tickets")
    public List<Ticket> searchForAllTickets()
    {
        return ticketService.getAllTickets();
    }

    @GetMapping("/search/ticket/{ticketId}")
    public @ResponseBody Ticket searchForTicket (@PathVariable Long ticketId)
    {
        return ticketService.getTicketById(ticketId);
    }

    @DeleteMapping("/delete/ticket/{ticketId}")
    public void deleteTicket(@PathVariable Long ticketId)
    {
        ticketService.deleteTicketById(ticketId);
    }
}
