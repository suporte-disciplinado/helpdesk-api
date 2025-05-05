package com.suportedisciplinado.api.service;

import com.suportedisciplinado.api.model.KnowledgeBaseTag;
import com.suportedisciplinado.api.repository.KnowledgeBaseTagRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KnowledgeBaseTagServiceTest {

    @Mock
    private KnowledgeBaseTagRepository repository;

    @InjectMocks
    private KnowledgeBaseTagService service;

    @Test
    void shouldReturnAllKnowledgeBaseTags() {
        KnowledgeBaseTag tag = new KnowledgeBaseTag();
        when(repository.findAll()).thenReturn(List.of(tag));

        List<KnowledgeBaseTag> result = service.findAll();
        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(tag);
    }
}
