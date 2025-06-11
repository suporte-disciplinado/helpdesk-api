package com.suportedisciplinado.api.repository;

import com.suportedisciplinado.api.model.Status;
import com.suportedisciplinado.api.model.Ticket;
import com.suportedisciplinado.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query("SELECT t FROM Ticket t " +
            "WHERE (:status IS NULL OR t.status = :status) " +
            "AND (:title IS NULL OR LOWER(t.title) LIKE LOWER(CONCAT('%', :title, '%'))) " )
    List<Ticket> searchTickets(@Param("status") Status status,
                               @Param("title") String title);

    @Query("SELECT t FROM Ticket t " +
            "WHERE t.assignedAgent = :agent " +
            "AND (:status IS NULL OR t.status = :status) " +
            "AND (:title IS NULL OR LOWER(t.title) LIKE LOWER(CONCAT('%', :title, '%')))")
    List<Ticket> searchTicketsByAssignedAgent(@Param("agent") User agent,
                                              @Param("status") Status status,
                                              @Param("title") String title);
}
