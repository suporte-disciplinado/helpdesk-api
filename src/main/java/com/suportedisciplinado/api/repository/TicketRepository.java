package com.suportedisciplinado.api.repository;

import com.suportedisciplinado.api.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>
{
    @Override
    Ticket getOne(Long id);

    @Override
    List<Ticket> findAll();

    @Override
    void deleteById(Long id);

    @Override
    <S extends Ticket> S saveAndFlush(S entity);
}
