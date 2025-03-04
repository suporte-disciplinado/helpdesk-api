package com.suportedisciplinado.api.model;

import jakarta.persistence.*;

@Entity
@Table(name="ticket_attachments")
public class TicketAttachments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


}
