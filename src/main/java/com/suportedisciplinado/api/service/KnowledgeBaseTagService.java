package com.suportedisciplinado.api.service;

import com.suportedisciplinado.api.model.KnowledgeBaseTag;
import com.suportedisciplinado.api.repository.KnowledgeBaseTagRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class KnowledgeBaseTagService {

    @Autowired
    private KnowledgeBaseTagRepository knowledgeBaseTagRepository;

    public List<KnowledgeBaseTag> findAll() {
        return knowledgeBaseTagRepository.findAll();
    }

    public ResponseEntity<List<KnowledgeBaseTag>> getAllKnowledgeBaseTags() {
        return ResponseEntity.ok(knowledgeBaseTagRepository.findAll());
    }

    public ResponseEntity<Optional<KnowledgeBaseTag>> getKnowledgeBaseTagById(Long id) {
        return ResponseEntity.ok(knowledgeBaseTagRepository.findById(id));
    }

    public ResponseEntity<KnowledgeBaseTag> createKnowledgeBaseTag(KnowledgeBaseTag knowledgeBaseTag) {
        return ResponseEntity.ok(knowledgeBaseTagRepository.saveAndFlush(knowledgeBaseTag));
    }

    public ResponseEntity<KnowledgeBaseTag> updateKnowledgeBaseTag(KnowledgeBaseTag knowledgeBaseTag) {
        Optional<KnowledgeBaseTag> findTag = knowledgeBaseTagRepository.findById(knowledgeBaseTag.getId());
        if (findTag.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        KnowledgeBaseTag tagToUpdate = findTag.get();
        tagToUpdate.setDescription(knowledgeBaseTag.getDescription());

        KnowledgeBaseTag updatedTag = knowledgeBaseTagRepository.saveAndFlush(tagToUpdate);
        return ResponseEntity.ok(knowledgeBaseTagRepository.saveAndFlush(updatedTag));
    }

    public ResponseEntity<String> deleteKnowledgeBaseTag(Long id) {
        knowledgeBaseTagRepository.deleteById(id);
        return ResponseEntity.ok("Tag deleted successfully!");
    }
}
