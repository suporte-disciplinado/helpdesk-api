package com.suportedisciplinado.api.service;

import com.suportedisciplinado.api.model.*;
import com.suportedisciplinado.api.repository.TicketAttachmentRepository;
import com.suportedisciplinado.api.repository.TicketCommentRepository;
import com.suportedisciplinado.api.repository.TicketRepository;
import com.suportedisciplinado.api.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TicketAttachmentService
{
    private final TicketAttachmentRepository attachmentRepository;
    private final TicketCommentRepository ticketCommentRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public TicketAttachmentService(
        TicketAttachmentRepository attachmentRepository,
        TicketCommentRepository ticketCommentRepository,
        TicketRepository ticketRepository,
        UserRepository userRepository
    )
    {
        this.attachmentRepository = attachmentRepository;
        this.ticketCommentRepository = ticketCommentRepository;
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<TicketAttachment> getAttachmentById(Long attachmentId)
    throws NullPointerException
    {
        Objects.requireNonNull(attachmentId, "The attachment id informed is null, please insert a valid id!");

        TicketAttachment attachment = attachmentRepository.getOne(attachmentId);
        validateAttachment(attachment);
        return ResponseEntity.ok(attachment);
    }

    public ResponseEntity<String> updateAttachment(TicketAttachment updatedAttachment)
    throws NullPointerException
    {
        validateAttachment(updatedAttachment);

        TicketAttachment attachmentToUpdate = getAttachmentById(updatedAttachment.getId()).getBody();
        User user = userRepository.getOne(updatedAttachment.getUser().getId());
        TicketComment ticketComment = ticketCommentRepository.getOne(updatedAttachment.getComment().getId());
        Ticket ticket = ticketRepository.getOne(updatedAttachment.getTicket().getId());

        attachmentToUpdate.setTicket(ticket);
        attachmentToUpdate.setComment(ticketComment);
        attachmentToUpdate.setUser(user);
        attachmentToUpdate.setFilePath(updatedAttachment.getFilePath());
        attachmentToUpdate.setFileType(updatedAttachment.getFileType());

        attachmentRepository.save(attachmentToUpdate);
        return ResponseEntity.ok("Attachment updated successfully!");
    }

    public ResponseEntity<List<TicketAttachment>> getAllAttachments() {
        return ResponseEntity.ok(attachmentRepository.findAll());
    }

    public ResponseEntity<String> deleteAttachmentById(Long id) {
        attachmentRepository.deleteById(id);
        return ResponseEntity.ok("Attachment deleted successfully!");
    }

    public ResponseEntity<String> createAttachment(TicketAttachment newAttachment) {
        validateAttachment(newAttachment);

        User user = userRepository.getOne(newAttachment.getUser().getId());
        TicketComment ticketComment = ticketCommentRepository.getOne(newAttachment.getComment().getId());
        Ticket ticket = ticketRepository.getOne(newAttachment.getTicket().getId());

        newAttachment.setTicket(ticket);
        newAttachment.setComment(ticketComment);
        newAttachment.setUser(user);

        attachmentRepository.saveAndFlush(newAttachment);
        return ResponseEntity.ok("Attachment created successfully!");
    }

    private void validateAttachment(TicketAttachment attachment)
    throws NullPointerException
    {
        Objects.requireNonNull(attachment, "The attachment received is null, please pass a valid attachment!");
    }
}
