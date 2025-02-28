package com.suportedisciplinado.api.controller;

import com.suportedisciplinado.api.model.TicketAttachment;
import com.suportedisciplinado.api.service.TicketAttachmentService;
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

    @PostMapping("/register/attachment")
    public void createAttachment(@RequestBody TicketAttachment newAttachment)
    {
        attachmentService.createAttachment(newAttachment);
    }

    @PostMapping("/update/attachment")
    public void updateAttachment(@RequestBody TicketAttachment updatedAttachment)
    {
        attachmentService.updateAttachment(updatedAttachment);
    }

    @GetMapping("/search/attachments")
    public List<TicketAttachment> searchForAllAttachments()
    {
        return attachmentService.getAllAttachments();
    }

    @GetMapping("/search/attachment/{attachmentId}")
    public @ResponseBody TicketAttachment searchForAttachment (@PathVariable Long attachmentId)
    {
        return attachmentService.getAttachmentById(attachmentId);
    }

    @DeleteMapping("/delete/attachment/{attachmentId}")
    public void deleteAttachment(@PathVariable Long attachmentId)
    {
        attachmentService.deleteAttachmentById(attachmentId);
    }
}
