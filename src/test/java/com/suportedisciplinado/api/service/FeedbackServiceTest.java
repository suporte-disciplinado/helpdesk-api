package com.suportedisciplinado.api.service;

import com.suportedisciplinado.api.model.Feedback;
import com.suportedisciplinado.api.repository.FeedbackRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class FeedbackServiceTest {

    @Mock
    private FeedbackRepository feedbackRepository;

    @InjectMocks
    private FeedbackService feedbackService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnAllFeedbacks() {
        List<Feedback> feedbacks = Arrays.asList(new Feedback(), new Feedback());
        when(feedbackRepository.findAll()).thenReturn(feedbacks);

        ResponseEntity<List<Feedback>> result = feedbackService.getAllFeedbacks();
        assertEquals(2, result.getBody().size());
        assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    void shouldReturnFeedbackByIdIfExists() {
        Feedback feedback = new Feedback();
        feedback.setId(1L);
        when(feedbackRepository.findById(1L)).thenReturn(Optional.of(feedback));

        ResponseEntity<Feedback> result = feedbackService.getFeedbackById(1L);
        assertEquals(200, result.getStatusCodeValue());
        assertEquals(1L, result.getBody().getId());
    }

    @Test
    void shouldReturnNotFoundIfFeedbackDoesNotExist() {
        when(feedbackRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Feedback> result = feedbackService.getFeedbackById(1L);
        assertEquals(404, result.getStatusCodeValue());
    }

    @Test
    void shouldCreateFeedback() {
        Feedback feedback = new Feedback();
        feedback.setRating(5);
        when(feedbackRepository.saveAndFlush(any(Feedback.class))).thenReturn(feedback);

        ResponseEntity<Feedback> result = feedbackService.createFeedback(feedback);
        assertEquals(200, result.getStatusCodeValue());
        assertEquals(5, result.getBody().getRating());
    }

    @Test
    void shouldUpdateExistingFeedback() {
        Feedback feedback = new Feedback();
        feedback.setId(1L);
        feedback.setComment("Updated");
        feedback.setRating(4);

        Feedback existing = new Feedback();
        existing.setId(1L);
        existing.setComment("Old");
        existing.setRating(2);

        when(feedbackRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(feedbackRepository.saveAndFlush(any(Feedback.class))).thenReturn(feedback);

        ResponseEntity<Feedback> result = feedbackService.updateFeedback(feedback);
        assertEquals(200, result.getStatusCodeValue());
        assertEquals("Updated", result.getBody().getComment());
    }

    @Test
    void shouldReturnNotFoundWhenUpdatingNonExistentFeedback() {
        Feedback feedback = new Feedback();
        feedback.setId(999L);
        when(feedbackRepository.findById(999L)).thenReturn(Optional.empty());

        ResponseEntity<Feedback> result = feedbackService.updateFeedback(feedback);
        assertEquals(404, result.getStatusCodeValue());
    }

    @Test
    void shouldDeleteFeedback() {
        doNothing().when(feedbackRepository).deleteById(1L);

        ResponseEntity<String> result = feedbackService.deleteFeedback(1L);
        assertEquals("Feedback deleted successfully!", result.getBody());
        assertEquals(200, result.getStatusCodeValue());
    }
}
