package com.suportedisciplinado.api.controller;

import com.suportedisciplinado.api.model.TicketAttachment;
import com.suportedisciplinado.api.service.TicketAttachmentService;
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

public class TicketAttachmentControllerTest {

    @Mock
    private TicketAttachmentService service;

    @InjectMocks
    private TicketAttachmentController controller;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateAttachment() {
        TicketAttachment ta = new TicketAttachment();
        ResponseEntity<String> serviceResponse = ResponseEntity.ok("Attachment created successfully!");
        when(service.createAttachment(ta)).thenReturn(serviceResponse);
        ResponseEntity<String> response = controller.createAttachment(ta);
        assertThat(response.getBody()).isEqualTo("Attachment created successfully!");
    }

    @Test
    public void testUpdateAttachment() {
        TicketAttachment ta = new TicketAttachment();
        ResponseEntity<String> serviceResponse = ResponseEntity.ok("Attachment updated successfully!");
        when(service.updateAttachment(ta)).thenReturn(serviceResponse);
        ResponseEntity<String> response = controller.updateAttachment(ta);
        assertThat(response.getBody()).isEqualTo("Attachment updated successfully!");
    }

    @Test
    public void testSearchForAllAttachments() {
        List<TicketAttachment> list = Collections.emptyList();
        ResponseEntity<List<TicketAttachment>> serviceResponse = ResponseEntity.ok(list);
        when(service.getAllAttachments()).thenReturn(serviceResponse);
        ResponseEntity<List<TicketAttachment>> response = controller.searchForAllAttachments();
        assertThat(response.getBody()).isEqualTo(list);
    }

    @Test
    public void testSearchForAttachment() {
        TicketAttachment ta = new TicketAttachment();
        ResponseEntity<TicketAttachment> serviceResponse = ResponseEntity.ok(ta);
        when(service.getAttachmentById(1L)).thenReturn(serviceResponse);
        ResponseEntity<TicketAttachment> response = controller.searchForAttachment(1L);
        assertThat(response.getBody()).isEqualTo(ta);
    }

    @Test
    public void testDeleteAttachment() {
        ResponseEntity<String> serviceResponse = ResponseEntity.ok("Attachment deleted successfully!");
        when(service.deleteAttachmentById(1L)).thenReturn(serviceResponse);
        ResponseEntity<String> response = controller.deleteAttachment(1L);
        assertThat(response.getBody()).isEqualTo("Attachment deleted successfully!");
    }
}
