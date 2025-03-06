package com.suportedisciplinado.api.service;

import com.suportedisciplinado.api.model.KnowledgeBaseCategory;
import com.suportedisciplinado.api.repository.KnowledgeBaseCategoryRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class KnowledgeBaseCategoryService {

    private final KnowledgeBaseCategoryRepository knowledgeBaseCategoryRepository;

    public KnowledgeBaseCategoryService(final KnowledgeBaseCategoryRepository knowledgeBaseCategoryRepository) {
        this.knowledgeBaseCategoryRepository = knowledgeBaseCategoryRepository;
    }

    public ResponseEntity<List<KnowledgeBaseCategory>> getAllKnowledgeBaseCategories() {
        return ResponseEntity.ok(knowledgeBaseCategoryRepository.findAll());
    }

    public ResponseEntity<Optional<KnowledgeBaseCategory>> getKnowledgeBaseCategoryById(Long id) {
        return ResponseEntity.ok(knowledgeBaseCategoryRepository.findById(id));
    }

    public ResponseEntity<KnowledgeBaseCategory> createKnowledgeBaseCategory(KnowledgeBaseCategory knowledgeBaseCategory) {
        return ResponseEntity.ok(knowledgeBaseCategoryRepository.saveAndFlush(knowledgeBaseCategory));
    }

    public ResponseEntity<KnowledgeBaseCategory> updateKnowledgeBaseCategory(KnowledgeBaseCategory knowledgeBaseCategory) {
        Optional<KnowledgeBaseCategory> findCategory = knowledgeBaseCategoryRepository.findById(knowledgeBaseCategory.getId());
        if (findCategory.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        KnowledgeBaseCategory categoryToUpdate = findCategory.get();
        categoryToUpdate.setDescription(knowledgeBaseCategory.getDescription());

        KnowledgeBaseCategory updatedCategory = knowledgeBaseCategoryRepository.saveAndFlush(categoryToUpdate);
        return ResponseEntity.ok(knowledgeBaseCategoryRepository.saveAndFlush(updatedCategory));
    }

    public ResponseEntity<String> deleteKnowledgeBaseCategory(Long id) {
        knowledgeBaseCategoryRepository.deleteById(id);
        return ResponseEntity.ok("Category deleted successfully!");
    }
}
