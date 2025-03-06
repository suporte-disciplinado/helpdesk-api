package com.suportedisciplinado.api.arbitraries;

import com.suportedisciplinado.api.model.*;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.Combinators;
import net.jqwik.api.Combinators.Combinator8;

import java.time.LocalDateTime;

public class CustomArbitraries {

    public static Arbitrary<User> validUser() {
        return Arbitraries.strings()
                .alpha()
                .ofMinLength(3)
                .ofMaxLength(20)
                .map(name -> new User(Arbitraries.longs().between(1, Long.MAX_VALUE).sample(), name, name + "@example.com", "hashedPassword", Role.USER, LocalDateTime.now()));
    }

    public static Arbitrary<Category> validCategory() {
        return Arbitraries.strings()
                .alpha()
                .ofMinLength(3)
                .ofMaxLength(20)
                .map(name -> {
                    Category category = new Category();
                    category.setName(name);
                    category.setDescription("Description for " + name);
                    return category;
                });
    }

    public static Arbitrary<String> validTitle() {
        return Arbitraries.strings()
                .alpha()
                .ofMinLength(5)
                .ofMaxLength(50);
    }

    public static Arbitrary<String> validDescription() {
        return Arbitraries.strings()
                .alpha()
                .ofMinLength(100)
                .ofMaxLength(500);
    }

    public static Arbitrary<Ticket> validTicket() {
        return Combinators.combine(
                Arbitraries.longs().between(1, Long.MAX_VALUE),
                validUser(),
                validUser(),
                validTitle(),
                validDescription(),
                validCategory(),
                Arbitraries.of(Priority.values()),
                Arbitraries.of(Status.values())
        ).as((id, user, assignedAgent, title, description, category, priority, status) -> {
            Ticket ticket = new Ticket();
            ticket.setId(id);
            ticket.setUser(user);
            ticket.setAssignedAgent(assignedAgent);
            ticket.setTitle(title);
            ticket.setDescription(description);
            ticket.setCategory(category);
            ticket.setPriority(priority);
            ticket.setStatus(status);
            return ticket;
        });
    }

    public static Arbitrary<TicketComment> validTicketComment() {
        Arbitrary<Long> idArb = Arbitraries.longs().between(1, Long.MAX_VALUE);
        Arbitrary<Ticket> ticketArb = validTicket();
        Arbitrary<User> userArb = validUser();
        Arbitrary<String> commentArb = Arbitraries.strings()
            .alpha()
            .ofMinLength(10)
            .ofMaxLength(200);

        return Combinators.combine(
                idArb, ticketArb, userArb, commentArb
        ).as((id, ticket, user, comment) -> {
            TicketComment tc = new TicketComment();
            tc.setId(id);
            tc.setTicket(ticket);
            tc.setUser(user);
            tc.setComment(comment);
            tc.setCreatedAt(LocalDateTime.now());
            return tc;
        });
    }
}
