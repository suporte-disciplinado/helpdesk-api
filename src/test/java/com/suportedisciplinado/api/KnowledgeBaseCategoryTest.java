package com.suportedisciplinado.api;

import com.suportedisciplinado.api.model.KnowledgeBaseCategory;
import jakarta.validation.constraints.Min;
import net.jqwik.api.*;
import net.jqwik.api.constraints.Positive;
import net.jqwik.api.constraints.StringLength;
import org.checkerframework.common.value.qual.MinLen;
import org.junit.jupiter.api.Assertions;

import static org.assertj.core.api.Assertions.assertThat;

public class KnowledgeBaseCategoryTest {

    @Provide
    Arbitrary<String> validDescriptions() {
        return Arbitraries.strings()
                .withChars('a', 'z') // restringe para caracteres alfabéticos
                .ofMinLength(1)
                .ofMaxLength(100);
    }

    @Property
    public void knowledgeBaseCategoryCreatedTest(
        @ForAll @Positive @Min(1) Long id,
        @ForAll("validDescriptions") @StringLength(min = 5) @MinLen(5) String description
    ) {
        KnowledgeBaseCategory knowledgeBaseCategory = new KnowledgeBaseCategory();
        knowledgeBaseCategory.setId(id);
        knowledgeBaseCategory.setDescription(description);

        Assertions.assertTrue(description.matches("^[a-zA-Z]+$"));
        assertThat(knowledgeBaseCategory.getId()).isGreaterThan(0);
        assertThat(knowledgeBaseCategory.getDescription()).isAlphabetic().hasSizeGreaterThanOrEqualTo(5);
    }
}
