package com.suportedisciplinado.api;

import com.suportedisciplinado.api.model.Category;
import jakarta.validation.constraints.Min;
import net.jqwik.api.*;
import net.jqwik.api.constraints.Positive;
import org.checkerframework.common.value.qual.MinLen;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoryTest {
    @Property
    public void ticketCommentCreationTest(
            @ForAll @Positive @Min(1) Long id,
            @ForAll String name,
            @ForAll @MinLen(100) String description
    )
    {
        Category category = new Category();
        category.setId(id);
        category.setName(name);
        category.setDescription(description);

        assertThat(category.getId()).isGreaterThan(0);
        assertThat(category.getDescription()).isAlphanumeric().hasSizeGreaterThanOrEqualTo(100);
        assertThat(category.getName()).isAlphanumeric();
    }
}
