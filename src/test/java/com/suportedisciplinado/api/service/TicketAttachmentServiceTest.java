package com.suportedisciplinado.api.service;

import com.suportedisciplinado.api.model.*;
import com.suportedisciplinado.api.repository.TicketAttachmentRepository;
import com.suportedisciplinado.api.repository.TicketCommentRepository;
import com.suportedisciplinado.api.repository.TicketRepository;
import com.suportedisciplinado.api.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TicketAttachmentServiceTest {

    @Mock
    private TicketAttachmentRepository attachmentRepository;

    @Mock
    private TicketCommentRepository ticketCommentRepository;

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TicketAttachmentService service;

    @Test
    void testFindAll() {
        when(attachmentRepository.findAll()).thenReturn(List.of(new TicketAttachment()));
        List<TicketAttachment> result = service.findAll();
        assertEquals(1, result.size());
    }

    @Test
    void testGetAttachmentById() {
        TicketAttachment attachment = new TicketAttachment();
        attachment.setId(1L);
        when(attachmentRepository.findById(1L)).thenReturn(Optional.of(attachment));

        ResponseEntity<TicketAttachment> response = service.getAttachmentById(1L);
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    void testCreateAttachment() {
        TicketAttachment attachment = new TicketAttachment();

        User user = new User();
        user.setId(1L);
        Ticket ticket = new Ticket();
        ticket.setId(1L);
        TicketComment comment = new TicketComment();
        comment.setId(1L);

        attachment.setUser(user);
        attachment.setTicket(ticket);
        attachment.setComment(comment);

        when(attachmentRepository.saveAndFlush(any(TicketAttachment.class))).thenReturn(attachment);

        ResponseEntity<String> response = service.createAttachment(attachment);
        assertEquals("Attachment created successfully!", response.getBody());
    }

    @Test
    void testUpdateAttachment() {
        TicketAttachment original = new TicketAttachment();
        original.setId(1L);

        TicketAttachment update = new TicketAttachment();
        update.setId(1L);

        User user = new User();
        user.setId(1L);
        Ticket ticket = new Ticket();
        ticket.setId(1L);
        TicketComment comment = new TicketComment();
        comment.setId(1L);

        update.setUser(user);
        update.setTicket(ticket);
        update.setComment(comment);
        update.setFilePath("/file.txt");
        update.setFileType("text/plain");

        when(attachmentRepository.findById(1L)).thenReturn(Optional.of(original));

        ResponseEntity<String> response = service.updateAttachment(update);
        assertEquals("Attachment updated successfully!", response.getBody());
    }

    @Test
    void testDeleteAttachment() {
        doNothing().when(attachmentRepository).deleteById(1L);
        ResponseEntity<String> response = service.deleteAttachmentById(1L);
        assertEquals("Attachment deleted successfully!", response.getBody());
    }
}
