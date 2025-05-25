package com.suportedisciplinado.api.controller;

import com.suportedisciplinado.api.model.Feedback;
import com.suportedisciplinado.api.repository.FeedbackRepository;
import com.suportedisciplinado.api.repository.TicketRepository;
import com.suportedisciplinado.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping
    public List<Feedback> getAllFeedbacks(){
        return feedbackRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable Long id) {
        Optional<Feedback> feedback = feedbackRepository.findById(id);
        return feedback.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Feedback> createFeedback(@RequestBody Feedback feedback) {
        if (feedback.getTicket() == null || feedback.getUser() == null) {
            return ResponseEntity.badRequest().build();
        }

        feedback.setFeedbackAt(new Date());
        Feedback saved = feedbackRepository.save(feedback);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Feedback> updateFeedback(@PathVariable Long id, @RequestBody Feedback updatedFeedback) {
        return feedbackRepository.findById(id).map(feedback -> {
            feedback.setRating(updatedFeedback.getRating());
            feedback.setComment(updatedFeedback.getComment());
            Feedback saved = feedbackRepository.save(feedback);
            return ResponseEntity.ok(saved);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Long id) {
        if (!feedbackRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        feedbackRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
