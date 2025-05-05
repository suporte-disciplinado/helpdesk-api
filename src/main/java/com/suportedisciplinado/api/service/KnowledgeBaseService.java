package com.suportedisciplinado.api.service;

import com.suportedisciplinado.api.model.KnowledgeBase;
import com.suportedisciplinado.api.repository.KnowledgeBaseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class KnowledgeBaseService {

    @Autowired
    private KnowledgeBaseRepository knowledgeBaseRepository;

    public List<KnowledgeBase> findAll() {
        return knowledgeBaseRepository.findAll();
    }

    public ResponseEntity<List<KnowledgeBase>> getAllKnowledgeBases() {
        return ResponseEntity.ok(knowledgeBaseRepository.findAll());
    }

    public ResponseEntity<Optional<KnowledgeBase>> getKnowledgeBaseById(Long id) {
        return ResponseEntity.ok(knowledgeBaseRepository.findById(id));
    }

    public ResponseEntity<KnowledgeBase> createKnowledgeBase(KnowledgeBase knowledgeBase) {
        return ResponseEntity.ok(knowledgeBaseRepository.saveAndFlush(knowledgeBase));
    }

    public ResponseEntity<KnowledgeBase> updateKnowledgeBase(KnowledgeBase knowledgeBase) {
        Optional<KnowledgeBase> findTag = knowledgeBaseRepository.findById(knowledgeBase.getId());
        if (findTag.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        KnowledgeBase tagToUpdate = findTag.get();
        tagToUpdate.setDescription(knowledgeBase.getDescription());

        KnowledgeBase updatedTag = knowledgeBaseRepository.saveAndFlush(tagToUpdate);
        return ResponseEntity.ok(knowledgeBaseRepository.saveAndFlush(updatedTag));
    }

    public ResponseEntity<String> deleteKnowledgeBase(Long id) {
        knowledgeBaseRepository.deleteById(id);
        return ResponseEntity.ok("KnowledgeBase deleted successfully!");
    }
}
