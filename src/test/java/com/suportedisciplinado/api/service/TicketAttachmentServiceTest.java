package com.suportedisciplinado.api.service;

import com.suportedisciplinado.api.model.TicketAttachment;
import com.suportedisciplinado.api.model.Ticket;
import com.suportedisciplinado.api.model.TicketComment;
import com.suportedisciplinado.api.model.User;
import com.suportedisciplinado.api.repository.TicketAttachmentRepository;
import com.suportedisciplinado.api.repository.TicketCommentRepository;
import com.suportedisciplinado.api.repository.TicketRepository;
import com.suportedisciplinado.api.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

public class TicketAttachmentServiceTest {

    @Mock
    private TicketAttachmentRepository attachmentRepo;

    @Mock
    private TicketCommentRepository commentRepo;

    @Mock
    private TicketRepository ticketRepo;

    @Mock
    private UserRepository userRepo;

    @InjectMocks
    private TicketAttachmentService service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAttachmentById_Null() {
        assertThrows(NullPointerException.class, () -> service.getAttachmentById(null));
    }

    @Test
    public void testGetAttachmentById_Valid() {
        TicketAttachment ta = new TicketAttachment();
        ta.setId(1L);
        when(attachmentRepo.getReferenceById(1L)).thenReturn(ta);
        ResponseEntity<TicketAttachment> response = service.getAttachmentById(1L);
        assertThat(response.getBody()).isEqualTo(ta);
    }

    @Test
    public void testUpdateAttachment() {
        TicketAttachment original = new TicketAttachment();
        original.setId(1L);
        TicketAttachment updated = new TicketAttachment();
        updated.setId(1L);
        User user = new User();
        user.setId(1L);
        Ticket ticket = new Ticket();
        ticket.setId(1L);
        TicketComment comment = new TicketComment();
        comment.setId(1L);
        updated.setUser(user);
        updated.setTicket(ticket);
        updated.setComment(comment);
        updated.setFilePath("newPath");
        updated.setFileType("newType");
        when(attachmentRepo.getReferenceById(1L)).thenReturn(original);
        when(userRepo.getReferenceById(1L)).thenReturn(user);
        when(ticketRepo.getReferenceById(1L)).thenReturn(ticket);
        when(commentRepo.getReferenceById(1L)).thenReturn(comment);
        ResponseEntity<String> response = service.updateAttachment(updated);
        assertThat(response.getBody()).isEqualTo("Attachment updated successfully!");
        verify(attachmentRepo).save(original);
    }

    @Test
    public void testGetAllAttachments() {
        List<TicketAttachment> list = new ArrayList<>();
        when(attachmentRepo.findAll()).thenReturn(list);
        ResponseEntity<List<TicketAttachment>> response = service.getAllAttachments();
        assertThat(response.getBody()).isEqualTo(list);
    }

    @Test
    public void testDeleteAttachmentById() {
        ResponseEntity<String> response = service.deleteAttachmentById(1L);
        assertThat(response.getBody()).isEqualTo("Attachment deleted successfully!");
        verify(attachmentRepo).deleteById(1L);
    }

    @Test
    public void testCreateAttachment() {
        TicketAttachment ta = new TicketAttachment();
        ta.setId(1L);
        User user = new User();
        user.setId(1L);
        Ticket ticket = new Ticket();
        ticket.setId(1L);
        TicketComment comment = new TicketComment();
        comment.setId(1L);
        ta.setUser(user);
        ta.setTicket(ticket);
        ta.setComment(comment);
        ta.setFilePath("path");
        ta.setFileType("type");
        when(userRepo.getReferenceById(1L)).thenReturn(user);
        when(ticketRepo.getReferenceById(1L)).thenReturn(ticket);
        when(commentRepo.getReferenceById(1L)).thenReturn(comment);
        ResponseEntity<String> response = service.createAttachment(ta);
        assertThat(response.getBody()).isEqualTo("Attachment created successfully!");
        verify(attachmentRepo).saveAndFlush(ta);
    }
}