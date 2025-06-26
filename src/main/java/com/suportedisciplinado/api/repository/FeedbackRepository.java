package com.suportedisciplinado.api.repository;

import com.suportedisciplinado.api.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    // Métodos customizados podem ser adicionados aqui futuramente
}