package com.suportedisciplinado.api;

import com.suportedisciplinado.api.model.KnowledgeBaseCategory;
import jakarta.validation.constraints.Min;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.Positive;
import org.checkerframework.common.value.qual.MinLen;

import static org.assertj.core.api.Assertions.assertThat;

public class KnowledgeBaseCategoryTest {

    @Property
    public void knowledgeBaseCategoryCreatedTest(
        @ForAll @Positive @Min(1) Long id,
        @ForAll @MinLen(5) String description
    ) {
        KnowledgeBaseCategory knowledgeBaseCategory = new KnowledgeBaseCategory();
        knowledgeBaseCategory.setId(id);
        knowledgeBaseCategory.setDescription(description);

        assertThat(knowledgeBaseCategory.getId()).isGreaterThan(0);
        assertThat(knowledgeBaseCategory.getDescription()).isAlphabetic().hasSizeGreaterThanOrEqualTo(5);
    }
}
