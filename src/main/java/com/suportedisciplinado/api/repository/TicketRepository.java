package com.suportedisciplinado.api.repository;

import com.suportedisciplinado.api.model.Ticket;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends PagingAndSortingRepository<Ticket, Long>
{
}
