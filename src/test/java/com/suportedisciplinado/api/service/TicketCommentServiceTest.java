package com.suportedisciplinado.api.service;

import com.suportedisciplinado.api.model.Ticket;
import com.suportedisciplinado.api.model.TicketComment;
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

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TicketCommentServiceTest {

    @Mock
    private TicketCommentRepository commentRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketCommentService commentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        when(commentRepository.findAll()).thenReturn(Collections.singletonList(new TicketComment()));
        List<TicketComment> result = commentService.findAll();
        assertEquals(1, result.size());
    }

    @Test
    void testGetCommentById() {
        TicketComment comment = new TicketComment();
        comment.setId(1L);
        when(commentRepository.getOne(1L)).thenReturn(comment);
        ResponseEntity<TicketComment> response = commentService.getCommentById(1L);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    void testCreateComment() {
        Ticket ticket = new Ticket();
        ticket.setId(1L);

        User user = new User();
        user.setId(1L);

        TicketComment comment = new TicketComment();
        comment.setUser(user);
        comment.setTicket(ticket);

        when(userRepository.getOne(1L)).thenReturn(user);
        when(ticketRepository.getOne(1L)).thenReturn(ticket);

        ResponseEntity<String> response = commentService.createComment(comment);
        assertEquals(200, response.getStatusCodeValue());
        verify(commentRepository).saveAndFlush(any(TicketComment.class));
    }

    @Test
    void testUpdateComment() {
        Ticket ticket = new Ticket();
        ticket.setId(1L);

        User user = new User();
        user.setId(1L);

        TicketComment updatedComment = new TicketComment();
        updatedComment.setId(1L);
        updatedComment.setComment("Updated");
        updatedComment.setUser(user);
        updatedComment.setTicket(ticket);

        TicketComment existing = new TicketComment();
        existing.setId(1L);

        when(commentRepository.getOne(1L)).thenReturn(existing);
        when(userRepository.getOne(1L)).thenReturn(user);
        when(ticketRepository.getOne(1L)).thenReturn(ticket);

        ResponseEntity<String> response = commentService.updateComment(updatedComment);
        assertEquals(200, response.getStatusCodeValue());
        verify(commentRepository).save(existing);
    }

    @Test
    void testDeleteCommentById() {
        ResponseEntity<String> response = commentService.deleteCommentById(1L);
        assertEquals(200, response.getStatusCodeValue());
        verify(commentRepository).deleteById(1L);
    }
}
