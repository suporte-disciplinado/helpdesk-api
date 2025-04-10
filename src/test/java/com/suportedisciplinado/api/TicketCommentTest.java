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
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

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

    @Test
    public void testDefaultInitialization() {
        TicketComment comment = new TicketComment();
        assertThat(comment.getCreatedAt()).isNotNull();
        assertThat(comment.getUpdatedAt()).isNull();
        assertThat(comment.getDeletedAt()).isNull();
        assertThat(comment.getCommentAttachmentsList()).isNotNull().isEmpty();
    }

    @Test
    public void testPreUpdateSetsUpdatedAt() {
        TicketComment comment = new TicketComment();
        assertThat(comment.getUpdatedAt()).isNull();
        comment.setUpdatedAt();
        assertThat(comment.getUpdatedAt()).isNotNull();
    }

    @Test
    public void testSettersAndGetters() {
        TicketComment comment = new TicketComment();
        Ticket ticket = new Ticket();
        ticket.setId(1L);
        User user = new User();
        user.setId(1L);
        comment.setId(1L);
        comment.setTicket(ticket);
        comment.setUser(user);
        comment.setComment("Sample comment");
        LocalDateTime deletedAt = LocalDateTime.now();
        comment.setDeletedAt(deletedAt);
        assertThat(comment.getId()).isEqualTo(1L);
        assertThat(comment.getTicket()).isEqualTo(ticket);
        assertThat(comment.getUser()).isEqualTo(user);
        assertThat(comment.getComment()).isEqualTo("Sample comment");
        assertThat(comment.getDeletedAt()).isEqualTo(deletedAt);
    }
}
