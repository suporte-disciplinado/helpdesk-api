package com.suportedisciplinado.api.repository;

import com.suportedisciplinado.api.model.KnowledgeBaseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KnowledgeBaseCategoryRepository extends JpaRepository<KnowledgeBaseCategory, Long> {

}
