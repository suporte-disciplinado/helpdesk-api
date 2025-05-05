package com.suportedisciplinado.api.service;

import com.suportedisciplinado.api.model.KnowledgeBaseCategory;
import com.suportedisciplinado.api.repository.KnowledgeBaseCategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KnowledgeBaseCategoryServiceTest {

    @Mock
    private KnowledgeBaseCategoryRepository repository;

    @InjectMocks
    private KnowledgeBaseCategoryService service;

    @Test
    void shouldReturnAllKnowledgeBaseCategories() {
        KnowledgeBaseCategory category = new KnowledgeBaseCategory();
        when(repository.findAll()).thenReturn(List.of(category));

        List<KnowledgeBaseCategory> result = service.findAll();
        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(category);
    }
}
