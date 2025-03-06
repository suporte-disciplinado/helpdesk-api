package com.suportedisciplinado.api;

import com.suportedisciplinado.api.arbitraries.CustomArbitraries;
import com.suportedisciplinado.api.model.Category;
import com.suportedisciplinado.api.model.Priority;
import com.suportedisciplinado.api.model.Status;
import com.suportedisciplinado.api.model.Ticket;
import com.suportedisciplinado.api.model.User;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.constraints.StringLength;
import net.jqwik.api.constraints.Positive;
import org.assertj.core.api.Assertions;

public class TicketTest {

    @Provide
    public static Arbitrary<User> validUser() {
        return CustomArbitraries.validUser();
    }

    @Provide
    public static Arbitrary<Category> validCategory() {
        return CustomArbitraries.validCategory();
    }

    @Provide
    public static Arbitrary<Priority> validPriority() {
        return Arbitraries.of(Priority.values());
    }

    @Provide
    public static Arbitrary<Status> validStatus() {
        return Arbitraries.of(Status.values());
    }

    @Property
    public void ticketCreationTest(
        @ForAll @Positive Long id,
        @ForAll("validUser") User user,
        @ForAll("validUser") User assignedAgent,
        @ForAll @StringLength(min = 3, max = 50) String title,
        @ForAll @StringLength(min = 100, max = 1000) String description,
        @ForAll("validCategory") Category category,
        @ForAll("validPriority") Priority priority,
        @ForAll("validStatus") Status status
    ) {
        Ticket ticket = new Ticket();
        ticket.setId(id);
        ticket.setUser(user);
        ticket.setAssignedAgent(assignedAgent);
        ticket.setTitle(title);
        ticket.setDescription(description);
        ticket.setCategory(category);
        ticket.setPriority(priority);
        ticket.setStatus(status);

        Assertions.assertThat(ticket.getId()).isGreaterThan(0);
        Assertions.assertThat(ticket.getUser()).isInstanceOf(User.class);
        Assertions.assertThat(ticket.getAssignedAgent()).isInstanceOf(User.class);
        Assertions.assertThat(ticket.getTitle()).isNotEmpty();
        Assertions.assertThat(ticket.getDescription()).hasSizeGreaterThanOrEqualTo(100);
        Assertions.assertThat(ticket.getCategory()).isInstanceOf(Category.class);
        Assertions.assertThat(ticket.getPriority()).isIn((Object[]) Priority.values());
        Assertions.assertThat(ticket.getStatus()).isIn((Object[]) Status.values());
    }
}
