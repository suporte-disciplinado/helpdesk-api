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

    private final FeedbackRepository repository;

    public FeedbackService(FeedbackRepository repository) {
        this.repository = repository;
    }

    public List<Feedback> findAll() {
        return repository.findAll();
    }

    public Optional<Feedback> findById(Long id) {
        return repository.findById(id);
    }

    public Feedback save(Feedback feedback) {
        return repository.saveAndFlush(feedback);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
