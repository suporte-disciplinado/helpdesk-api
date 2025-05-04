package com.suportedisciplinado.api.controller;

import com.suportedisciplinado.api.model.KnowledgeBaseTag;
import com.suportedisciplinado.api.service.KnowledgeBaseTagService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/knowledge-base/tag")
public class KnowledgeBaseTagController {

    private final KnowledgeBaseTagService knowledgeBaseTagService;

    public KnowledgeBaseTagController(KnowledgeBaseTagService knowledgeBaseTagService) {
        this.knowledgeBaseTagService = knowledgeBaseTagService;
    }

    @GetMapping
    public ResponseEntity<List<KnowledgeBaseTag>> getAll() {
        return knowledgeBaseTagService.getAllKnowledgeBaseTags();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<KnowledgeBaseTag>> getById(@PathVariable Long id) {
        return knowledgeBaseTagService.getKnowledgeBaseTagById(id);
    }

    @PostMapping
    public ResponseEntity<KnowledgeBaseTag> create(@RequestBody KnowledgeBaseTag knowledgeBaseTag) {
        return knowledgeBaseTagService.createKnowledgeBaseTag(knowledgeBaseTag);
    }

    @PutMapping
    public ResponseEntity<KnowledgeBaseTag> update(@RequestBody KnowledgeBaseTag knowledgeBaseTag) {
        return knowledgeBaseTagService.updateKnowledgeBaseTag(knowledgeBaseTag);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return knowledgeBaseTagService.deleteKnowledgeBaseTag(id);
    }
}
