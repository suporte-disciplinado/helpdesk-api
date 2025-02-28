package com.suportedisciplinado.api.service;

import com.suportedisciplinado.api.model.Category;
import com.suportedisciplinado.api.model.Ticket;
import com.suportedisciplinado.api.model.TicketAttachment;
import com.suportedisciplinado.api.model.TicketComment;
import com.suportedisciplinado.api.repository.TicketAttachmentRepository;
import com.suportedisciplinado.api.repository.TicketCommentRepository;
import com.suportedisciplinado.api.repository.TicketRepository;
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

    public TicketAttachment getAttachmentById(Long attachmentId)
    throws NullPointerException
    {
        Objects.requireNonNull(attachmentId, "O id do anexo informado está nulo, por favor informe um id válido!");

        TicketAttachment attachment = attachmentRepository.getOne(attachmentId);
        validateAttachment(attachment);
        return attachment;
    }

    public void updateAttachment(TicketAttachment updatedAttachment)
    throws NullPointerException
    {
        validateAttachment(updatedAttachment);

        TicketAttachment attachmentToUpdate = getAttachmentById(updatedAttachment.getId());
        User user = userRepository.getOne(updatedAttachment.getUser().getId());
        TicketComment ticketComment = ticketCommentRepository.getOne(updatedAttachment.getComment().getId());
        Ticket ticket = ticketRepository.getOne(updatedAttachment.getTicket().getId());

        attachmentToUpdate.setTicket(ticket);
        attachmentToUpdate.setComment(ticketComment);
        attachmentToUpdate.setUser(user);
        attachmentToUpdate.setFilePath(updatedAttachment.getFilePath());
        attachmentToUpdate.setFileType(updatedAttachment.getFileType());

        attachmentRepository.save(attachmentToUpdate);
    }

    public List<TicketAttachment> getAllAttachments() {
        return attachmentRepository.findAll();
    }

    public void deleteAttachmentById(Long id) {
        attachmentRepository.deleteById(id);
    }

    public void createAttachment(TicketAttachment newAttachment) {
        validateAttachment(newAttachment);

        User user = userRepository.getOne(newAttachment.getUser().getId());
        TicketComment ticketComment = ticketCommentRepository.getOne(newAttachment.getComment().getId());
        Ticket ticket = ticketRepository.getOne(newAttachment.getTicket().getId());

        newAttachment.setTicket(ticket);
        newAttachment.setComment(ticketComment);
        newAttachment.setUser(user);

        attachmentRepository.saveAndFlush(newAttachment);
    }

    private void validateAttachment(TicketAttachment attachment)
    throws NullPointerException
    {
        Objects.requireNonNull(attachment, "O anexo fornecido não pode ser nulo, por favor forneca um anexo válido!");
    }
}
