package com.suportedisciplinado.api;

import com.suportedisciplinado.api.model.Ticket;
import com.suportedisciplinado.api.model.TicketAttachment;
import com.suportedisciplinado.api.model.TicketComment;
import jakarta.validation.constraints.Min;
import net.jqwik.api.*;
import net.jqwik.api.constraints.Positive;
import org.checkerframework.common.value.qual.MinLen;

import static org.assertj.core.api.Assertions.assertThat;

public class TicketAttachmentTest {
    @Provide
    Arbitrary<User> validUser() {
        return Arbitraries.defaultFor(User.class);
    }

    @Provide
    Arbitrary<Ticket> validTicket() {
        return Arbitraries.defaultFor(Ticket.class);
    }

    @Provide
    Arbitrary<TicketComment> validTicketComment() {
        return Arbitraries.defaultFor(TicketComment.class);
    }

    @Property
    public void ticketCommentCreationTest(
            @ForAll @Positive @Min(1) Long id,
            @ForAll("validTicket") Ticket ticket,
            @ForAll("validTicketComment") TicketComment ticketComment,
            @ForAll("validUser") User user,
            @ForAll @MinLen(100) String filePath,
            @ForAll String fileType
    )
    {
        TicketAttachment ticketAttachment = new TicketAttachment();
        ticketAttachment.setId(id);
        ticketAttachment.setUser(user);
        ticketAttachment.setTicket(ticket);
        ticketAttachment.setComment(ticketComment);
        ticketAttachment.setFilePath(filePath);
        ticketAttachment.setFileType(fileType);

        assertThat(ticketAttachment.getId()).isGreaterThan(0);
        assertThat(ticketAttachment.getUser()).isInstanceOf(User.class);
        assertThat(ticketAttachment.getTicket()).isInstanceOf(Ticket.class);
        assertThat(ticketAttachment.getComment()).isInstanceOf(TicketComment.class);
        assertThat(ticketAttachment.getFilePath()).isAlphanumeric().hasSizeGreaterThanOrEqualTo(100);
        assertThat(ticketAttachment.getFileType()).isAlphanumeric();
    }
}
