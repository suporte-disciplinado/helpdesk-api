package com.suportedisciplinado.api.controller;

import com.suportedisciplinado.api.model.TicketComment;
import com.suportedisciplinado.api.service.TicketCommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.util.Collections;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class TicketCommentControllerTest {

    @Mock
    private TicketCommentService service;

    @InjectMocks
    private TicketCommentController controller;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateComment() {
        TicketComment tc = new TicketComment();
        ResponseEntity<String> serviceResponse = ResponseEntity.ok("Comment created successfully!");
        when(service.createComment(tc)).thenReturn(serviceResponse);
        ResponseEntity<String> response = controller.createComment(tc);
        assertThat(response.getBody()).isEqualTo("Comment created successfully!");
    }

    @Test
    public void testUpdateComment() {
        TicketComment tc = new TicketComment();
        ResponseEntity<String> serviceResponse = ResponseEntity.ok("Comment updated successfully!");
        when(service.updateComment(tc)).thenReturn(serviceResponse);
        ResponseEntity<String> response = controller.updateComment(tc);
        assertThat(response.getBody()).isEqualTo("Comment updated successfully!");
    }

    @Test
    public void testSearchForAllComments() {
        List<TicketComment> list = Collections.emptyList();
        ResponseEntity<List<TicketComment>> serviceResponse = ResponseEntity.ok(list);
        when(service.getAllComments()).thenReturn(serviceResponse);
        ResponseEntity<List<TicketComment>> response = controller.searchForAllComments();
        assertThat(response.getBody()).isEqualTo(list);
    }

    @Test
    public void testSearchForComment() {
        TicketComment tc = new TicketComment();
        ResponseEntity<TicketComment> serviceResponse = ResponseEntity.ok(tc);
        when(service.getCommentById(1L)).thenReturn(serviceResponse);
        ResponseEntity<TicketComment> response = controller.searchForComment(1L);
        assertThat(response.getBody()).isEqualTo(tc);
    }

    @Test
    public void testDeleteComment() {
        ResponseEntity<String> serviceResponse = ResponseEntity.ok("Comment deleted successfully!");
        when(service.deleteCommentById(1L)).thenReturn(serviceResponse);
        ResponseEntity<String> response = controller.deleteComment(1L);
        assertThat(response.getBody()).isEqualTo("Comment deleted successfully!");
    }
}