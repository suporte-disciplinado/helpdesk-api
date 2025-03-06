package com.suportedisciplinado.api.controller;

import com.suportedisciplinado.api.model.KnowledgeBaseCategory;
import com.suportedisciplinado.api.service.KnowledgeBaseCategoryService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/knowledge-base/category")
public class KnowledgeBaseCategoryController {

    private final KnowledgeBaseCategoryService knowledgeBaseCategoryService;

    public KnowledgeBaseCategoryController(KnowledgeBaseCategoryService knowledgeBaseCategoryService) {
        this.knowledgeBaseCategoryService = knowledgeBaseCategoryService;
    }

    @GetMapping
    public ResponseEntity<List<KnowledgeBaseCategory>> getAll() {
        return knowledgeBaseCategoryService.getAllKnowledgeBaseCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<KnowledgeBaseCategory>> getById(@PathVariable Long id) {
        return knowledgeBaseCategoryService.getKnowledgeBaseCategoryById(id);
    }

    @PostMapping
    public ResponseEntity<KnowledgeBaseCategory> create(@RequestBody KnowledgeBaseCategory knowledgeBaseCategory) {
        return knowledgeBaseCategoryService.createKnowledgeBaseCategory(knowledgeBaseCategory);
    }

    @PutMapping
    public ResponseEntity<KnowledgeBaseCategory> update(@RequestBody KnowledgeBaseCategory knowledgeBaseCategory) {
        return knowledgeBaseCategoryService.updateKnowledgeBaseCategory(knowledgeBaseCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return knowledgeBaseCategoryService.deleteKnowledgeBaseCategory(id);
    }
}
