package com.suportedisciplinado.api.service;

import com.suportedisciplinado.api.model.KnowledgeBaseTag;
import com.suportedisciplinado.api.repository.KnowledgeBaseTagRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class KnowledgeBaseTagServiceTest {

    @InjectMocks
    private KnowledgeBaseTagService knowledgeBaseTagService;

    @Mock
    private KnowledgeBaseTagRepository knowledgeBaseTagRepository;

    private KnowledgeBaseTag knowledgeBaseTag;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        knowledgeBaseTag = new KnowledgeBaseTag();
        knowledgeBaseTag.setId(10L);
        knowledgeBaseTag.setDescription("Tag 01");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllKnowledgeBaseTags() {
        List<KnowledgeBaseTag> tags = Arrays.asList(
                new KnowledgeBaseTag(1L, "Tag 1"),
                new KnowledgeBaseTag(2L, "Tag 2")
        );
        when(knowledgeBaseTagRepository.findAll()).thenReturn(tags);

        ResponseEntity<List<KnowledgeBaseTag>> response = knowledgeBaseTagService.getAllKnowledgeBaseTags();

        verify(knowledgeBaseTagRepository).findAll();
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void getKnowledgeBaseTagById() {
        Long tagId = 10L;
        when(knowledgeBaseTagRepository.findById(tagId)).thenReturn(Optional.of(knowledgeBaseTag));

        ResponseEntity<Optional<KnowledgeBaseTag>> response = knowledgeBaseTagService.getKnowledgeBaseTagById(tagId);

        verify(knowledgeBaseTagRepository).findById(tagId);
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().isPresent());

        KnowledgeBaseTag returnedTag = response.getBody().get();
        assertEquals(knowledgeBaseTag.getId(), returnedTag.getId());
        assertEquals(knowledgeBaseTag.getDescription(), returnedTag.getDescription());
    }

    @Test
    void createKnowledgeBaseTag() {
        knowledgeBaseTag = new KnowledgeBaseTag();
        knowledgeBaseTag.setId(11L);
        knowledgeBaseTag.setDescription("Tag 02");

        when(knowledgeBaseTagRepository.saveAndFlush(knowledgeBaseTag)).thenReturn(knowledgeBaseTag);

        ResponseEntity<KnowledgeBaseTag> response = knowledgeBaseTagService.createKnowledgeBaseTag(knowledgeBaseTag);

        verify(knowledgeBaseTagRepository).saveAndFlush(knowledgeBaseTag);
        assertNotNull(response);
        assertEquals(knowledgeBaseTag, response.getBody());
    }

    @Test
    void updateKnowledgeBaseTag() {

    }

    @Test
    void deleteKnowledgeBaseTag() {

    }
}