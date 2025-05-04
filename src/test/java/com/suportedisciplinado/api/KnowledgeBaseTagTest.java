package com.suportedisciplinado.api;

import com.suportedisciplinado.api.model.KnowledgeBaseTag;
import jakarta.validation.constraints.Min;
import net.jqwik.api.*;
import net.jqwik.api.constraints.Positive;
import org.junit.jupiter.api.Assertions;

import static org.assertj.core.api.Assertions.assertThat;

public class KnowledgeBaseTagTest {

    @Provide
    Arbitrary<String> validDescriptions() {
        return Arbitraries.strings()
                .withChars('a', 'z') // restringe para caracteres alfabéticos
                .ofMinLength(1)
                .ofMaxLength(100);
    }

    @Property
    public void knowledgeBaseTagCreatedTest(
        @ForAll @Positive @Min(1) Long id,
        @ForAll("validDescriptions") String description
    ) {
        KnowledgeBaseTag knowledgeBaseTag = new KnowledgeBaseTag();
        knowledgeBaseTag.setId(id);
        knowledgeBaseTag.setDescription(description);

        Assertions.assertTrue(description.matches("^[a-zA-Z]+$"));
        assertThat(knowledgeBaseTag.getId()).isGreaterThan(0);
        assertThat(knowledgeBaseTag.getDescription()).isAlphabetic();
    }
}
