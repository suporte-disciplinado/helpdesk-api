package com.suportedisciplinado.api.service;

import com.suportedisciplinado.api.model.Ticket;
import com.suportedisciplinado.api.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @Mock
    private TicketRepository repository;

    @InjectMocks
    private TicketService service;

    @Test
    void shouldReturnAllTickets() {
        Ticket ticket = new Ticket();
        when(repository.findAll()).thenReturn(List.of(ticket));

        List<Ticket> result = service.findAll();
        assertThat(result).hasSize(1);
        assertThat(result.getFirst()).isEqualTo(ticket);
    }
}
