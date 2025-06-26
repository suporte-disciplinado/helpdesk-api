package com.suportedisciplinado.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suportedisciplinado.api.config.SecurityConfigTest;
import com.suportedisciplinado.api.model.Ticket;
import com.suportedisciplinado.api.model.TicketAttachment;
import com.suportedisciplinado.api.model.TicketComment;
import com.suportedisciplinado.api.model.User;
import com.suportedisciplinado.api.service.TicketAttachmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(SecurityConfigTest.class)
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = TicketAttachmentController.class)
public class TicketAttachmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TicketAttachmentService attachmentService;

    @Test
    @WithMockUser
    void shouldReturnAllAttachments() throws Exception {
        when(attachmentService.getAllAttachments()).thenReturn(ResponseEntity.ok(List.of(new TicketAttachment())));

        mockMvc.perform(get("/api/ticket/attachment")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldReturnAttachmentById() throws Exception {
        TicketAttachment attachment = new TicketAttachment();
        attachment.setId(1L);
        when(attachmentService.getAttachmentById(1L)).thenReturn(ResponseEntity.ok(attachment));

        mockMvc.perform(get("/api/ticket/attachment/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldCreateAttachment() throws Exception {
        TicketAttachment attachment = new TicketAttachment();
        attachment.setFilePath("/uploads/example.pdf");
        attachment.setFileType("application/pdf");

        TicketComment comment = new TicketComment();
        comment.setId(1L);
        attachment.setComment(comment);

        Ticket ticket = new Ticket();
        ticket.setId(1L);
        attachment.setTicket(ticket);

        User user = new User();
        user.setId(1L);
        attachment.setUser(user);

        when(attachmentService.createAttachment(any(TicketAttachment.class))).thenReturn(ResponseEntity.ok("Attachment created successfully!"));

        mockMvc.perform(post("/api/ticket/attachment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(attachment)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldUpdateAttachment() throws Exception {
        TicketAttachment attachment = new TicketAttachment();
        attachment.setId(1L);
        attachment.setFilePath("/uploads/updated.pdf");
        attachment.setFileType("application/pdf");

        TicketComment comment = new TicketComment();
        comment.setId(1L);
        attachment.setComment(comment);

        Ticket ticket = new Ticket();
        ticket.setId(1L);
        attachment.setTicket(ticket);

        User user = new User();
        user.setId(1L);
        attachment.setUser(user);

        when(attachmentService.updateAttachment(any(TicketAttachment.class))).thenReturn(ResponseEntity.ok("Attachment updated successfully!"));

        mockMvc.perform(put("/api/ticket/attachment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(attachment)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldDeleteAttachment() throws Exception {
        when(attachmentService.deleteAttachmentById(1L)).thenReturn(ResponseEntity.ok("Attachment deleted successfully!"));

        mockMvc.perform(delete("/api/ticket/attachment/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
