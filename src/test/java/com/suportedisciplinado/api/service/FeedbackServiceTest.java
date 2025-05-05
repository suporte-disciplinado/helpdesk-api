package com.suportedisciplinado.api.service;

import com.suportedisciplinado.api.model.Feedback;
import com.suportedisciplinado.api.repository.FeedbackRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FeedbackServiceTest {

    @Mock
    private FeedbackRepository repository;

    @InjectMocks
    private FeedbackService service;

    @Test
    void shouldReturnAllFeedbacks() {
        Feedback feedback = new Feedback();
        when(repository.findAll()).thenReturn(List.of(feedback));

        List<Feedback> result = service.findAll();
        assertThat(result).hasSize(1);
        assertThat(result.getFirst()).isEqualTo(feedback);
    }
}
