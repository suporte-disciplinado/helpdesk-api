package com.suportedisciplinado.api;

import com.suportedisciplinado.api.model.TicketAttachment;
import com.suportedisciplinado.api.model.Ticket;
import com.suportedisciplinado.api.model.TicketComment;
import com.suportedisciplinado.api.model.User;
import net.jqwik.api.Property;
import net.jqwik.api.ForAll;
import net.jqwik.api.Provide;
import net.jqwik.api.constraints.StringLength;
import net.jqwik.api.constraints.AlphaChars;
import net.jqwik.api.constraints.Positive;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

public class TicketAttachmentTest {

    @Provide
    public net.jqwik.api.Arbitrary<User> validUser() {
        return com.suportedisciplinado.api.arbitraries.CustomArbitraries.validUser();
    }

    @Provide
    public net.jqwik.api.Arbitrary<Ticket> validTicket() {
        return com.suportedisciplinado.api.arbitraries.CustomArbitraries.validTicket();
    }

    @Provide
    public net.jqwik.api.Arbitrary<TicketComment> validTicketComment() {
        return com.suportedisciplinado.api.arbitraries.CustomArbitraries.validTicketComment();
    }

    @Property
    public void ticketAttachmentCreationTest(
            @ForAll @Positive @Min(1) Long id,
            @ForAll("validTicket") Ticket ticket,
            @ForAll("validTicketComment") TicketComment ticketComment,
            @ForAll("validUser") User user,
            @ForAll @StringLength(min = 1) @AlphaChars String filePath,
            @ForAll @StringLength(min = 1, max = 100) @AlphaChars String fileType
    ) {
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
        assertThat(ticketAttachment.getFilePath()).isNotBlank();
        assertThat(ticketAttachment.getFileType()).isAlphanumeric();
        assertThat(ticketAttachment.getCreatedAt()).isBeforeOrEqualTo(LocalDateTime.now());
    }
}
