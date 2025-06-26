package com.suportedisciplinado.api;

import com.suportedisciplinado.api.model.Category;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.AlphaChars;
import net.jqwik.api.constraints.Positive;
import net.jqwik.api.constraints.StringLength;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class CategoryTest {
    @Property
    public void ticketCommentCreationTest(
            @ForAll @Positive Long id,
            @ForAll @StringLength(min = 6, max = 25) @AlphaChars String name,
            @ForAll @StringLength(min = 100, max = 1000) @AlphaChars String description
    ) {
        Category category = new Category();
        category.setId(id);
        category.setName(name);
        category.setDescription(description);

        assertThat(category.getId()).isGreaterThan(0);
        assertThat(category.getDescription()).isAlphanumeric().hasSizeGreaterThanOrEqualTo(100);
        assertThat(category.getName()).isAlphanumeric();
    }

    @Property
    void shouldCreateValidCategory(
        @ForAll @StringLength(min = 6, max = 30) @AlphaChars String name,
        @ForAll @StringLength(min = 100, max = 300) @AlphaChars String description
    ) {
        Category category = new Category();
        category.setId(1L);
        category.setName(name);
        category.setDescription(description);

        assertThat(category.getId()).isEqualTo(1L);
        assertThat(category.getName()).isNotBlank().isAlphabetic();
        assertThat(category.getDescription()).hasSizeBetween(100, 300);
    }

    @Test
    void shouldAllowAddingTicketToCategoryManually() {
        Category category = new Category();
        category.setId(2L);
        category.setName("Infra");
        category.setDescription("Infraestrutura básica da empresa");

        assertThat(category.getName()).isEqualTo("Infra");
        assertThat(category.getDescription()).contains("Infraestrutura");
    }
}
