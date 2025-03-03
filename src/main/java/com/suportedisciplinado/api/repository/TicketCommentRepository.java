package com.suportedisciplinado.api.repository;

import com.suportedisciplinado.api.model.TicketComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketCommentRepository extends JpaRepository<TicketComment, Long>
{
    @Override
    TicketComment getOne(Long id);

    @Override
    List<TicketComment> findAll();

    @Override
    void deleteById(Long id);

    @Override
    <S extends TicketComment> S saveAndFlush(S entity);
}
