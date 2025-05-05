package com.suportedisciplinado.api.service;

import com.suportedisciplinado.api.model.KnowledgeBase;
import com.suportedisciplinado.api.repository.KnowledgeBaseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KnowledgeBaseServiceTest {

    @Mock
    private KnowledgeBaseRepository repository;

    @InjectMocks
    private KnowledgeBaseService service;

    @Test
    void shouldReturnAllKnowledgeBaseEntries() {
        KnowledgeBase base = new KnowledgeBase();
        when(repository.findAll()).thenReturn(Collections.singletonList(base));

        List<KnowledgeBase> result = service.findAll();
        assertThat(result).hasSize(1);
        assertThat(result.getFirst()).isEqualTo(base);
    }
}
