package com.suportedisciplinado.api;

import com.suportedisciplinado.api.model.KnowledgeBase;
import com.suportedisciplinado.api.model.KnowledgeBaseCategory;
import com.suportedisciplinado.api.model.KnowledgeBaseTag;
import jakarta.validation.constraints.Min;
import net.jqwik.api.*;
import net.jqwik.api.constraints.Positive;
import org.checkerframework.common.value.qual.MinLen;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class KnowledgeBaseTest {

    @Provide
    Arbitrary<KnowledgeBaseCategory> validKnowledgeBaseCategory() {
        return Arbitraries.defaultFor(KnowledgeBaseCategory.class);
    }

    @Provide
    Arbitrary<KnowledgeBaseTag> validKnowledgeBaseTag() {
        return Arbitraries.defaultFor(KnowledgeBaseTag.class);
    }

    @Property
    public void knowledgeBaseCreatedTest(
        @ForAll @Positive @Min(1) Long id,
        @ForAll @MinLen(5) String description,
        @ForAll @MinLen(100) String annotation,
        @ForAll String author,
        @ForAll("validKnowledgeBaseCategory") KnowledgeBaseCategory category,
        @ForAll("validKnowledgeBaseTag") KnowledgeBaseTag tag

    ) {
        KnowledgeBase knowledgeBase = new KnowledgeBase();
        knowledgeBase.setId(id);
        knowledgeBase.setDescription(description);
        knowledgeBase.setAnnotation(annotation);
        knowledgeBase.setAuthor(author);
        knowledgeBase.setCategory(category);
        knowledgeBase.setTags(Set.of(tag));

        assertThat(knowledgeBase.getId()).isGreaterThan(0);
        assertThat(knowledgeBase.getDescription()).isAlphabetic().hasSizeGreaterThanOrEqualTo(5);
        assertThat(knowledgeBase.getAnnotation()).isAlphabetic().hasSizeGreaterThanOrEqualTo(100);
        assertThat(knowledgeBase.getAuthor()).isAlphabetic();
        assertThat(knowledgeBase.getCategory()).isInstanceOf(KnowledgeBaseCategory.class);
        assertThat(knowledgeBase.getTags()).isInstanceOf(KnowledgeBaseTag.class);
    }
}
