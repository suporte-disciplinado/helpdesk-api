package com.suportedisciplinado.api.service;

import com.suportedisciplinado.api.model.KnowledgeBaseTag;
import com.suportedisciplinado.api.repository.KnowledgeBaseTagRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class KnowledgeBaseTagServiceTest {

    @Mock
    private KnowledgeBaseTagRepository tagRepository;

    @InjectMocks
    private KnowledgeBaseTagService tagService;

    private KnowledgeBaseTag sampleTag;

    @BeforeEach
    void setup() {
        sampleTag = new KnowledgeBaseTag();
        sampleTag.setId(1L);
        sampleTag.setDescription("Suporte");
    }

    @Test
    void testFindAll() {
        when(tagRepository.findAll()).thenReturn(List.of(sampleTag));

        List<KnowledgeBaseTag> tags = tagService.findAll();

        assertEquals(1, tags.size());
        assertEquals("Suporte", tags.get(0).getDescription());
        verify(tagRepository).findAll();
    }

    @Test
    void testGetAllKnowledgeBaseTags() {
        when(tagRepository.findAll()).thenReturn(List.of(sampleTag));

        ResponseEntity<List<KnowledgeBaseTag>> response = tagService.getAllKnowledgeBaseTags();

        assertEquals(200, response.getStatusCodeValue());
        assertFalse(response.getBody().isEmpty());
    }

    @Test
    void testGetKnowledgeBaseTagById() {
        when(tagRepository.findById(1L)).thenReturn(Optional.of(sampleTag));

        ResponseEntity<Optional<KnowledgeBaseTag>> response = tagService.getKnowledgeBaseTagById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().isPresent());
        assertEquals("Suporte", response.getBody().get().getDescription());
    }

    @Test
    void testCreateKnowledgeBaseTag() {
        when(tagRepository.saveAndFlush(any(KnowledgeBaseTag.class))).thenReturn(sampleTag);

        ResponseEntity<KnowledgeBaseTag> response = tagService.createKnowledgeBaseTag(sampleTag);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Suporte", response.getBody().getDescription());
    }

    @Test
    void testUpdateKnowledgeBaseTag_Success() {
        when(tagRepository.findById(1L)).thenReturn(Optional.of(sampleTag));
        when(tagRepository.saveAndFlush(any(KnowledgeBaseTag.class))).thenReturn(sampleTag);

        sampleTag.setDescription("Atualizada");

        ResponseEntity<KnowledgeBaseTag> response = tagService.updateKnowledgeBaseTag(sampleTag);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Atualizada", response.getBody().getDescription());
    }

    @Test
    void testUpdateKnowledgeBaseTag_NotFound() {
        when(tagRepository.findById(99L)).thenReturn(Optional.empty());

        KnowledgeBaseTag notFoundTag = new KnowledgeBaseTag();
        notFoundTag.setId(99L);

        ResponseEntity<KnowledgeBaseTag> response = tagService.updateKnowledgeBaseTag(notFoundTag);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void testDeleteKnowledgeBaseTag() {
        doNothing().when(tagRepository).deleteById(1L);

        ResponseEntity<String> response = tagService.deleteKnowledgeBaseTag(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Tag deleted successfully!", response.getBody());
    }
} 
