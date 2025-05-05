package com.suportedisciplinado.api.controller;

import com.suportedisciplinado.api.model.KnowledgeBase;
import com.suportedisciplinado.api.repository.KnowledgeBaseRepository;
import com.suportedisciplinado.api.service.KnowledgeBaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class KnowledgeBaseControllerTest {

    @Autowired
    private KnowledgeBaseRepository repository;

    private KnowledgeBaseService service;
    private KnowledgeBaseController controller;

    @BeforeEach
    void setup() {
        service = new KnowledgeBaseService();
        service.knowledgeBaseRepository = repository;
        controller = new KnowledgeBaseController(service);
    }

    @Test
    void testCreateKnowledgeBase() {
        KnowledgeBase kb = new KnowledgeBase();
        kb.setDescription("Test Description");

        ResponseEntity<KnowledgeBase> response = controller.create(kb);
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("Test Description", response.getBody().getDescription());
    }

    @Test
    void testGetAllKnowledgeBases() {
        ResponseEntity<List<KnowledgeBase>> response = controller.getAll();
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
    }

    @Test
    void testGetKnowledgeBaseById() {
        KnowledgeBase kb = new KnowledgeBase();
        kb.setDescription("GetById Test");
        KnowledgeBase saved = repository.saveAndFlush(kb);

        ResponseEntity<Optional<KnowledgeBase>> response = controller.getById(saved.getId());
        assertEquals(200, response.getStatusCode().value());
        assertTrue(response.getBody().isPresent());
        assertEquals("GetById Test", response.getBody().get().getDescription());
    }

    @Test
    void testUpdateKnowledgeBase() {
        KnowledgeBase kb = new KnowledgeBase();
        kb.setDescription("Initial");
        KnowledgeBase saved = repository.saveAndFlush(kb);

        saved.setDescription("Updated");
        ResponseEntity<KnowledgeBase> response = controller.update(saved);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Updated", response.getBody().getDescription());
    }

    @Test
    void testDeleteKnowledgeBase() {
        KnowledgeBase kb = new KnowledgeBase();
        kb.setDescription("To be deleted");
        KnowledgeBase saved = repository.saveAndFlush(kb);

        ResponseEntity<String> response = controller.delete(saved.getId());

        assertEquals(200, response.getStatusCode().value());
        assertEquals("KnowledgeBase deleted successfully!", response.getBody());
        assertFalse(repository.findById(saved.getId()).isPresent());
    }
}
