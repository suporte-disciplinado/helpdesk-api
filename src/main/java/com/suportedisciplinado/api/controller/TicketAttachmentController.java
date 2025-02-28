package com.suportedisciplinado.api.controller;

import com.suportedisciplinado.api.model.TicketAttachment;
import com.suportedisciplinado.api.service.TicketAttachmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticket/attachment")
public class TicketAttachmentController
{
    private final TicketAttachmentService attachmentService;

    public TicketAttachmentController(TicketAttachmentService attachmentService)
    {
        this.attachmentService = attachmentService;
    }

    @PostMapping
    public ResponseEntity<String> createAttachment(@RequestBody TicketAttachment newAttachment)
    {
        return attachmentService.createAttachment(newAttachment);
    }

    @PutMapping
    public ResponseEntity<String> updateAttachment(@RequestBody TicketAttachment updatedAttachment)
    {
        return attachmentService.updateAttachment(updatedAttachment);
    }

    @GetMapping
    public ResponseEntity<List<TicketAttachment>> searchForAllAttachments()
    {
        return attachmentService.getAllAttachments();
    }

    @GetMapping("/{attachmentId}")
    public ResponseEntity<TicketAttachment> searchForAttachment (@PathVariable Long attachmentId)
    {
        return attachmentService.getAttachmentById(attachmentId);
    }

    @DeleteMapping("/{attachmentId}")
    public ResponseEntity<String> deleteAttachment(@PathVariable Long attachmentId)
    {
        return attachmentService.deleteAttachmentById(attachmentId);
    }
}
