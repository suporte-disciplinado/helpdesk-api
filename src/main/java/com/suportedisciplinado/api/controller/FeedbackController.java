package com.suportedisciplinado.api.controller;

import com.suportedisciplinado.api.model.Feedback;
import com.suportedisciplinado.api.service.FeedbackService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping
    public ResponseEntity<List<Feedback>> getAllFeedbacks(){
        return feedbackService.getAllFeedbacks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable Long id) {
        return feedbackService.getFeedbackById(id);
    }

    @PostMapping
    public ResponseEntity<Feedback> createFeedback(@RequestBody Feedback newFeedback) {
        return feedbackService.createFeedback(newFeedback);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Feedback> updateFeedback(@RequestBody Feedback updatedFeedback) {
        return feedbackService.updateFeedback(updatedFeedback);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFeedback(@PathVariable Long id) {
        return feedbackService.deleteFeedback(id);
    }
}
