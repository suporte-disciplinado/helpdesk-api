package com.suportedisciplinado.api.service;

import com.suportedisciplinado.api.repository.TicketCommentRepository;
import org.springframework.stereotype.Service;

@Service
public class TicketCommentService
{
    private final TicketCommentRepository ticketCommentRepository;

    public TicketCommentService(TicketCommentRepository ticketCommentRepository)
    {
        this.ticketCommentRepository = ticketCommentRepository;
    }
}
