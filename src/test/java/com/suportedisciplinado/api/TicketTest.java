package com.suportedisciplinado.api;

import com.suportedisciplinado.api.model.Category;
import com.suportedisciplinado.api.model.Priority;
import com.suportedisciplinado.api.model.Status;
import com.suportedisciplinado.api.model.Ticket;
import jakarta.validation.constraints.Min;
import net.jqwik.api.*;
import net.jqwik.api.constraints.Positive;
import org.checkerframework.common.value.qual.MinLen;

import static org.assertj.core.api.Assertions.assertThat;

public class TicketTest
{
    @Provide
    Arbitrary<User> validUser() {
        return Arbitraries.defaultFor(User.class);
    }

    @Provide
    Arbitrary<Category> validCategory() {
        return Arbitraries.defaultFor(Category.class);
    }

    @Provide
    Arbitrary<Priority> validPriority() {
        return Arbitraries.of(Priority.values());
    }

    @Provide
    Arbitrary<Status> validStatus() {
        return Arbitraries.of(Status.values());
    }

    @Property
    public void ticketCreationTest(
        @ForAll @Positive @Min(1) Long id,
        @ForAll("validUser") User user,
        @ForAll("validUser") User assignedAgent,
        @ForAll String title,
        @ForAll @MinLen(100) String description,
        @ForAll("validCategory") Category category,
        @ForAll("validPriority") Priority priority,
        @ForAll("validStatus") Status status
    )
    {
        Ticket ticket = new Ticket();
        ticket.setId(id);
        ticket.setUser(user);
        ticket.setUser(assignedAgent);
        ticket.setTitle(title);
        ticket.setDescription(description);
        ticket.setCategory(category);
        ticket.setPriority(priority);
        ticket.setStatus(status);

        assertThat(ticket.getId()).isGreaterThan(0);
        assertThat(ticket.getUser()).isInstanceOf(User.class);
        assertThat(ticket.getAssignedAgent()).isInstanceOf(User.class);
        assertThat(ticket.getTitle()).isAlphanumeric();
        assertThat(ticket.getDescription()).isAlphanumeric().hasSizeGreaterThanOrEqualTo(100);
        assertThat(ticket.getCategory()).isInstanceOf(Category.class);
        assertThat(ticket.getPriority()).isIn((Object) Priority.values());
        assertThat(ticket.getStatus()).isIn((Object) Status.values());
    }
}
