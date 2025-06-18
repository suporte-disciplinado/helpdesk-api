package com.suportedisciplinado.api;

import com.suportedisciplinado.api.model.KnowledgeBaseCategory;
import jakarta.validation.constraints.Min;
import net.jqwik.api.*;
import net.jqwik.api.constraints.AlphaChars;
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

    @Property
    void shouldCreateValidKnowledgeBaseCategory(
        @ForAll @Positive Long id,
        @ForAll @AlphaChars @StringLength(min = 5, max = 100) String description
    ) {
        KnowledgeBaseCategory category = new KnowledgeBaseCategory();
        category.setId(id);
        category.setDescription(description);

        assertThat(category.getId()).isPositive();
        assertThat(category.getDescription()).isNotBlank()
                                             .hasSizeBetween(5, 100)
                                             .isAlphabetic();
    }

    @Example
    void shouldUseAllArgsConstructor() {
        KnowledgeBaseCategory category = new KnowledgeBaseCategory(10L, "Infraestrutura");

        assertThat(category.getId()).isEqualTo(10L);
        assertThat(category.getDescription()).isEqualTo("Infraestrutura");
    }
}
