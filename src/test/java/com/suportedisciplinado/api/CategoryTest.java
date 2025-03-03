package com.suportedisciplinado.api;

import com.suportedisciplinado.api.model.Category;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.AlphaChars;
import net.jqwik.api.constraints.Positive;
import net.jqwik.api.constraints.StringLength;
import static org.assertj.core.api.Assertions.assertThat;

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
}
