package com.suportedisciplinado.api.service;

import com.suportedisciplinado.api.model.Feedback;
import com.suportedisciplinado.api.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {

    @Autowired
    public FeedbackRepository feedbackRepository;

    public ResponseEntity<List<Feedback>> getAllFeedbacks() {
        return ResponseEntity.ok(feedbackRepository.findAll());
    }

    public ResponseEntity<Feedback> getFeedbackById(Long id) {
        Optional<Feedback> findFeedback = feedbackRepository.findById(id);
        return findFeedback.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Feedback> createFeedback(Feedback feedback) {
        return ResponseEntity.ok(feedbackRepository.saveAndFlush(feedback));
    }

    public ResponseEntity<Feedback> updateFeedback(Feedback feedback) {
        Optional<Feedback> findFeedback = feedbackRepository.findById(feedback.getId());
        if (findFeedback.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Feedback feedbackToUpdate = findFeedback.get();
        feedbackToUpdate.setComment(feedback.getComment());
        feedbackToUpdate.setRating(feedback.getRating());

        Feedback updatedFeedback = feedbackRepository.saveAndFlush(feedbackToUpdate);
        return ResponseEntity.ok(feedbackRepository.saveAndFlush(updatedFeedback));
    }

    public ResponseEntity<String> deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
        return ResponseEntity.ok("Feedback deleted successfully!");
    }
}
