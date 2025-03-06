package com.suportedisciplinado.api.repository;

import com.suportedisciplinado.api.model.KnowledgeBaseTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KnowledgeBaseTagRepository extends JpaRepository<KnowledgeBaseTag, Long>{
}
