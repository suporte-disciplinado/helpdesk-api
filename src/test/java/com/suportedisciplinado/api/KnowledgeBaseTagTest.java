package com.suportedisciplinado.api;

import com.suportedisciplinado.api.model.KnowledgeBaseTag;
import jakarta.validation.constraints.Min;
import net.jqwik.api.*;
import net.jqwik.api.constraints.Positive;

import static org.assertj.core.api.Assertions.assertThat;

public class KnowledgeBaseTagTest {

    @Property
    public void knowledgeBaseTagCreatedTest(
        @ForAll @Positive @Min(1) Long id,
        @ForAll String description
    ) {
        KnowledgeBaseTag knowledgeBaseTag = new KnowledgeBaseTag();
        knowledgeBaseTag.setId(id);
        knowledgeBaseTag.setDescription(description);

        assertThat(knowledgeBaseTag.getId()).isGreaterThan(0);
        assertThat(knowledgeBaseTag.getDescription()).isAlphabetic();
    }
}
