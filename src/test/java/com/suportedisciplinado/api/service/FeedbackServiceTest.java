package com.suportedisciplinado.api.service;

import com.suportedisciplinado.api.model.Feedback;
import com.suportedisciplinado.api.repository.FeedbackRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FeedbackServiceTest {

    @Mock
    private FeedbackRepository repository;

    @InjectMocks
    private FeedbackService service;

    @Test
    void findAll() {
        Feedback feedback = new Feedback();
        when(repository.findAll()).thenReturn(List.of(feedback));

        ResponseEntity<List<Feedback>> result = service.getAllFeedbacks();
        List<Feedback> body = result.getBody();

        assertThat(body).hasSize(1);
        assertThat(body.getFirst()).isEqualTo(feedback);
    }

    @Test
    void findById() {
        Feedback feedback = new Feedback();
        feedback.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(feedback));

        ResponseEntity<Feedback> result = service.getFeedbackById(1L);
        Feedback body = result.getBody();

        assertNotNull(body);
        assertEquals(1L, body.getId());
    }

    @Test
    void save() {
        Feedback feedback = new Feedback();
        feedback.setRating(5);
        when(repository.saveAndFlush(feedback)).thenReturn(feedback);

        Feedback saved  = service.createFeedback(feedback).getBody();

        assertNotNull(saved);
        assertEquals(5, saved.getRating());
    }

    @Test
    void deleteById() {
        doNothing().when(repository).deleteById(1L);

        assertDoesNotThrow(() -> service.deleteFeedback(1L));
        verify(repository, times(1)).deleteById(1L);
    }
}