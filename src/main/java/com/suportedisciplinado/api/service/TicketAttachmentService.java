package com.suportedisciplinado.api.service;

import com.suportedisciplinado.api.repository.TicketAttachmentRepository;
import org.springframework.stereotype.Service;

@Service
public class TicketAttachmentService
{
    private final TicketAttachmentRepository ticketAttachmentRepository;

    public TicketAttachmentService(TicketAttachmentRepository ticketAttachmentRepository)
    {
        this.ticketAttachmentRepository = ticketAttachmentRepository;
    }
}
