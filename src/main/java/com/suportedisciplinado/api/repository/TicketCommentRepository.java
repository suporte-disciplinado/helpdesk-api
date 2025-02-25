package com.suportedisciplinado.api.repository;

import com.suportedisciplinado.api.model.TicketComment;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TicketCommentRepository extends PagingAndSortingRepository<TicketComment, Long>
{
}
