package com.suportedisciplinado.api;

import com.suportedisciplinado.api.model.KnowledgeBase;
import com.suportedisciplinado.api.model.KnowledgeBaseCategory;
import com.suportedisciplinado.api.model.KnowledgeBaseTag;
import jakarta.validation.constraints.Min;
import net.jqwik.api.*;
import net.jqwik.api.constraints.AlphaChars;
import net.jqwik.api.constraints.Positive;
import net.jqwik.api.constraints.StringLength;
import org.checkerframework.common.value.qual.MinLen;

import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class KnowledgeBaseTest {

    @Provide
    Arbitrary<KnowledgeBaseCategory> validKnowledgeBaseCategory() {
        return Arbitraries.strings()
                .withChars('a', 'z')
                .ofMinLength(3)
                .ofMaxLength(50)
                .map(desc -> {
                    KnowledgeBaseCategory cat = new KnowledgeBaseCategory();
                    cat.setDescription(desc);
                    return cat;
                });
    }

    @Provide
    Arbitrary<KnowledgeBaseTag> validKnowledgeBaseTag() {
        return Arbitraries.strings()
                .withChars('a', 'z') // apenas letras
                .ofMinLength(3)
                .ofMaxLength(50)
                .map(desc -> {
                    KnowledgeBaseTag tag = new KnowledgeBaseTag();
                    tag.setDescription(desc);
                    return tag;
                });
    }

    @Provide
    Arbitrary<String> validDescriptions() {
        return Arbitraries.strings()
                .withChars('a', 'z') // restringe para caracteres alfabéticos
                .ofMinLength(1)
                .ofMaxLength(100);
    }

    @Property
    void knowledgeBaseCreatedTest(
        @ForAll @Positive @Min(1) Long id,
        @ForAll("validDescriptions") @StringLength(min = 5) @MinLen(5) String description,
        @ForAll("validDescriptions") @StringLength(min = 100) @MinLen(100) String annotation,
        @ForAll("validDescriptions") @StringLength(min = 5) @MinLen(5) String author,
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
        assertThat(knowledgeBase.getTags())
                .allSatisfy(t -> assertThat(t).isInstanceOf(KnowledgeBaseTag.class));
    }

    @Provide
    Arbitrary<KnowledgeBaseCategory> validCategories() {
        return Arbitraries.strings()
                .withChars('a', 'z')
                .ofMinLength(5)
                .ofMaxLength(50)
                .map(desc -> {
                    KnowledgeBaseCategory cat = new KnowledgeBaseCategory();
                    cat.setId(1L);
                    cat.setDescription(desc);
                    return cat;
                });
    }

    @Provide
    Arbitrary<Set<KnowledgeBaseTag>> validTags() {
    return Arbitraries.strings()
        .withChars('a', 'z')
        .ofMinLength(3)
        .ofMaxLength(30)
        .map(desc -> {
            KnowledgeBaseTag tag = new KnowledgeBaseTag();
            tag.setId(1L);
            tag.setDescription(desc);
            return tag;
        })
        .set().ofMinSize(1).ofMaxSize(5); // fornece um Set<KnowledgeBaseTag>
    }

    @Property
    void shouldCreateValidKnowledgeBase(
        @ForAll @Positive Long id,
        @ForAll @AlphaChars @StringLength(min = 5, max = 100) String description,
        @ForAll @AlphaChars @StringLength(min = 100, max = 500) String annotation,
        @ForAll @AlphaChars @StringLength(min = 5, max = 50) String author,
        @ForAll("validCategories") KnowledgeBaseCategory category,
        @ForAll("validTags") Set<KnowledgeBaseTag> tags,
        @ForAll boolean active,
        @ForAll("validDateTimes") LocalDateTime updatedAt
    ) {
        KnowledgeBase kb = new KnowledgeBase();
        kb.setId(id);
        kb.setDescription(description);
        kb.setAnnotation(annotation);
        kb.setAuthor(author);
        kb.setCategory(category);
        kb.setTags(tags);
        kb.setActive(active);
        kb.setUpdatedAt(updatedAt);

        assertThat(kb.getId()).isPositive();
        assertThat(kb.getDescription()).isAlphabetic().hasSizeBetween(5, 100);
        assertThat(kb.getAnnotation()).isAlphabetic().hasSizeGreaterThanOrEqualTo(100);
        assertThat(kb.getAuthor()).isAlphabetic().hasSizeBetween(5, 50);
        assertThat(kb.getCategory()).isNotNull();
        assertThat(kb.getTags()).isNotEmpty().allSatisfy(t -> assertThat(t).isInstanceOf(KnowledgeBaseTag.class));
        assertThat(kb.getActive()).isNotNull();
        assertThat(kb.getUpdatedAt()).isNotNull();
    }

    @Provide
    Arbitrary<LocalDateTime> validDateTimes() {
        return Arbitraries.defaultFor(LocalDateTime.class)
                .filter(dt -> dt.isBefore(LocalDateTime.now().plusDays(1)));
    }
}
