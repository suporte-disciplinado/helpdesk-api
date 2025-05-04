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
    public void ticketCommentCreationTest(
        @ForAll @Positive Long id,
        @ForAll("validTicket") Ticket ticket,
        @ForAll("validTicketComment") TicketComment ticketComment,
        @ForAll("validUser") User user,
        @ForAll @StringLength(min = 1) @AlphaChars String filePath,
        @ForAll @StringLength(min = 1, max = 100) @AlphaChars String fileType
    ) {
        TicketAttachment ta = new TicketAttachment();
        ta.setId(id);
        ta.setUser(user);
        ta.setTicket(ticket);
        ta.setComment(ticketComment);
        ta.setFilePath(filePath);
        ta.setFileType(fileType);
        assertThat(ta.getId()).isGreaterThan(0);
        assertThat(ta.getUser()).isInstanceOf(User.class);
        assertThat(ta.getTicket()).isInstanceOf(Ticket.class);
        assertThat(ta.getComment()).isInstanceOf(TicketComment.class);
        assertThat(ta.getFileType()).isAlphanumeric();
    }

    @Test
    public void preUpdateSetsUpdatedAt() {
        TicketAttachment ta = new TicketAttachment();
        ta.setUpdatedAt(null);
        ta.setUpdatedAt();
        assertThat(ta.getUpdatedAt()).isNotNull();
    }
}
