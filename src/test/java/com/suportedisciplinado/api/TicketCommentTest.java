package com.suportedisciplinado.api;

import static org.assertj.core.api.Assertions.assertThat;

import com.suportedisciplinado.api.arbitraries.CustomArbitraries;
import com.suportedisciplinado.api.model.Ticket;
import com.suportedisciplinado.api.model.TicketComment;
import com.suportedisciplinado.api.model.User;

import jakarta.validation.constraints.Min;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.constraints.AlphaChars;
import net.jqwik.api.constraints.Positive;
import net.jqwik.api.constraints.StringLength;

public class TicketCommentTest {
    @Provide
    Arbitrary<User> validUser() {
        return CustomArbitraries.validUser();
    }

    @Provide
    Arbitrary<Ticket> validTicket() {
        return CustomArbitraries.validTicket();
    }

    @Property
    public void ticketCommentCreationTest(
            @ForAll @Positive @Min(1) Long id,
            @ForAll("validTicket") Ticket ticket,
            @ForAll("validUser") User user,
            @ForAll @StringLength(min = 100) @AlphaChars String comment
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
