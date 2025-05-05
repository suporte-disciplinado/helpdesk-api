package com.suportedisciplinado.api.service;

import com.suportedisciplinado.api.model.TicketComment;
import com.suportedisciplinado.api.repository.TicketCommentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketCommentServiceTest {

    @Mock
    private TicketCommentRepository repository;

    @InjectMocks
    private TicketCommentService service;

    @Test
    void shouldReturnAllComments() {
        TicketComment comment = new TicketComment();
        when(repository.findAll()).thenReturn(List.of(comment));

        List<TicketComment> result = service.findAll();
        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(comment);
    }
}
