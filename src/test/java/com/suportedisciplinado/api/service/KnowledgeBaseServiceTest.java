package com.suportedisciplinado.api.service;

import com.suportedisciplinado.api.model.KnowledgeBase;
import com.suportedisciplinado.api.repository.KnowledgeBaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class KnowledgeBaseServiceTest {

    @Mock
    private KnowledgeBaseRepository repository;

    @InjectMocks
    private KnowledgeBaseService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_shouldReturnList() {
        KnowledgeBase base = new KnowledgeBase();
        when(repository.findAll()).thenReturn(List.of(base));

        List<KnowledgeBase> result = service.findAll();

        assertThat(result).hasSize(1);
        assertThat(result.getFirst()).isEqualTo(base);
    }

    @Test
    void getKnowledgeBaseById_shouldReturnEntity() {
        KnowledgeBase base = new KnowledgeBase();
        base.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(base));

        ResponseEntity<Optional<KnowledgeBase>> response = service.getKnowledgeBaseById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertThat(response.getBody()).isPresent();
        assertEquals(1L, response.getBody().get().getId());
    }

    @Test
    void createKnowledgeBase_shouldReturnCreated() {
        KnowledgeBase base = new KnowledgeBase();
        base.setId(2L);
        when(repository.saveAndFlush(any(KnowledgeBase.class))).thenReturn(base);

        ResponseEntity<KnowledgeBase> response = service.createKnowledgeBase(base);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2L, response.getBody().getId());
    }

    @Test
    void updateKnowledgeBase_shouldReturnUpdatedEntity() {
        KnowledgeBase base = new KnowledgeBase();
        base.setId(1L);
        base.setDescription("Updated");

        when(repository.findById(1L)).thenReturn(Optional.of(base));
        when(repository.saveAndFlush(any(KnowledgeBase.class))).thenReturn(base);

        ResponseEntity<KnowledgeBase> response = service.updateKnowledgeBase(base);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Updated", response.getBody().getDescription());
    }

    @Test
    void deleteKnowledgeBase_shouldCallRepositoryAndReturnSuccess() {
        doNothing().when(repository).deleteById(1L);

        ResponseEntity<String> response = service.deleteKnowledgeBase(1L);

        verify(repository, times(1)).deleteById(1L);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("KnowledgeBase deleted successfully!", response.getBody());
    }
} 
