package com.suportedisciplinado.api.service;

import com.suportedisciplinado.api.model.TicketAttachment;
import com.suportedisciplinado.api.repository.TicketAttachmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketAttachmentServiceTest {

    @Mock
    private TicketAttachmentRepository repository;

    @InjectMocks
    private TicketAttachmentService service;

    @Test
    void shouldReturnAllAttachments() {
        TicketAttachment attachment = new TicketAttachment();
        when(repository.findAll()).thenReturn(List.of(attachment));

        List<TicketAttachment> result = service.findAll();
        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(attachment);
    }
}
