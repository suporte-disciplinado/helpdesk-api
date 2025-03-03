package com.suportedisciplinado.api;

import com.suportedisciplinado.api.model.*;
import jakarta.validation.constraints.Min;
import net.jqwik.api.*;
import net.jqwik.api.constraints.Positive;
import org.checkerframework.common.value.qual.MinLen;

import static org.assertj.core.api.Assertions.assertThat;

public class TicketCommentTest {
    @Provide
    Arbitrary<User> validUser() {
        return Arbitraries.defaultFor(User.class);
    }

    @Provide
    Arbitrary<Ticket> validTicket() {
        return Arbitraries.defaultFor(Ticket.class);
    }

    @Property
    public void ticketCommentCreationTest(
            @ForAll @Positive @Min(1) Long id,
            @ForAll("validTicket") Ticket ticket,
            @ForAll("validUser") User user,
            @ForAll @MinLen(100) String comment
    )
    {
        TicketComment ticketComment = new TicketComment();
        ticketComment.setId(id);
        ticketComment.setUser(user);
        ticketComment.setTicket(ticket);
        ticketComment.setComment(comment);

        assertThat(ticketComment.getId()).isGreaterThan(0);
        assertThat(ticketComment.getUser()).isInstanceOf(User.class);
        assertThat(ticketComment.getTicket()).isInstanceOf(Ticket.class);
        assertThat(ticketComment.getComment()).isAlphanumeric().hasSizeGreaterThanOrEqualTo(100);
    }
}
