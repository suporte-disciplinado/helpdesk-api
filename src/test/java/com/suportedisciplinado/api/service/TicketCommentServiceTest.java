package com.suportedisciplinado.api.service;

import com.suportedisciplinado.api.model.TicketComment;
import com.suportedisciplinado.api.model.Ticket;
import com.suportedisciplinado.api.model.User;
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

public class TicketCommentServiceTest {

    @Mock
    private TicketCommentRepository commentRepo;

    @Mock
    private UserRepository userRepo;

    @Mock
    private TicketRepository ticketRepo;

    @InjectMocks
    private TicketCommentService service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCommentById_Null() {
        assertThrows(NullPointerException.class, () -> service.getCommentById(null));
    }

    @Test
    public void testGetCommentById_Valid() {
        TicketComment tc = new TicketComment();
        tc.setId(1L);
        when(commentRepo.getOne(1L)).thenReturn(tc);
        ResponseEntity<TicketComment> response = service.getCommentById(1L);
        assertThat(response.getBody()).isEqualTo(tc);
    }

    @Test
    public void testUpdateComment() {
        TicketComment original = new TicketComment();
        original.setId(1L);
        TicketComment updated = new TicketComment();
        updated.setId(1L);
        User user = new User();
        user.setId(1L);
        Ticket ticket = new Ticket();
        ticket.setId(1L);
        updated.setUser(user);
        updated.setTicket(ticket);
        updated.setComment("Updated Comment");
        when(commentRepo.getOne(1L)).thenReturn(original);
        when(userRepo.getOne(1L)).thenReturn(user);
        when(ticketRepo.getOne(1L)).thenReturn(ticket);
        ResponseEntity<String> response = service.updateComment(updated);
        assertThat(response.getBody()).isEqualTo("Comment updated successfully!");
        verify(commentRepo).save(original);
        assertThat(original.getComment()).isEqualTo("Updated Comment");
    }

    @Test
    public void testGetAllComments() {
        List<TicketComment> list = new ArrayList<>();
        when(commentRepo.findAll()).thenReturn(list);
        ResponseEntity<List<TicketComment>> response = service.getAllComments();
        assertThat(response.getBody()).isEqualTo(list);
    }

    @Test
    public void testDeleteCommentById() {
        ResponseEntity<String> response = service.deleteCommentById(1L);
        assertThat(response.getBody()).isEqualTo("Comment deleted successfully!");
        verify(commentRepo).deleteById(1L);
    }

    @Test
    public void testCreateComment() {
        TicketComment tc = new TicketComment();
        tc.setId(1L);
        User user = new User();
        user.setId(1L);
        Ticket ticket = new Ticket();
        ticket.setId(1L);
        tc.setUser(user);
        tc.setTicket(ticket);
        tc.setComment("New Comment");
        when(userRepo.getReferenceById(1L)).thenReturn(user);
        when(ticketRepo.getReferenceById(1L)).thenReturn(ticket);
        ResponseEntity<String> response = service.createComment(tc);
        assertThat(response.getBody()).isEqualTo("Comment created successfully!");
        verify(commentRepo).saveAndFlush(tc);
    }
}
