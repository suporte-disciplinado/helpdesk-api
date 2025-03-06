package com.suportedisciplinado.api.controller;

import com.suportedisciplinado.api.model.KnowledgeBase;
import com.suportedisciplinado.api.service.KnowledgeBaseService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/knowledge-base")
public class KnowledgeBaseController {

    private final KnowledgeBaseService knowledgeBaseService;

    public KnowledgeBaseController(KnowledgeBaseService knowledgeBaseService) {
        this.knowledgeBaseService = knowledgeBaseService;
    }

    @GetMapping
    public ResponseEntity<List<KnowledgeBase>> getAll() {
        return knowledgeBaseService.getAllKnowledgeBases();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<KnowledgeBase>> getById(@PathVariable Long id) {
        return knowledgeBaseService.getKnowledgeBaseById(id);
    }

    @PostMapping
    public ResponseEntity<KnowledgeBase> create(@RequestBody KnowledgeBase knowledgeBase) {
        return knowledgeBaseService.createKnowledgeBase(knowledgeBase);
    }

    @PutMapping
    public ResponseEntity<KnowledgeBase> update(@RequestBody KnowledgeBase knowledgeBase) {
        return knowledgeBaseService.updateKnowledgeBase(knowledgeBase);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return knowledgeBaseService.deleteKnowledgeBase(id);
    }
}
