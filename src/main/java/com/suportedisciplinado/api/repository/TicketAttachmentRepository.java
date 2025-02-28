package com.suportedisciplinado.api.repository;

import com.suportedisciplinado.api.model.TicketAttachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketAttachmentRepository extends JpaRepository<TicketAttachment, Long>
{
    @Override
    TicketAttachment getOne(Long id);

    @Override
    List<TicketAttachment> findAll();

    @Override
    void deleteById(Long id);

    @Override
    <S extends TicketAttachment> S saveAndFlush(S entity);
}
