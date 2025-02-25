package com.suportedisciplinado.api.repository;

import com.suportedisciplinado.api.model.TicketAttachment;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TicketAttachmentRepository extends PagingAndSortingRepository<TicketAttachment, Long>
{
}
