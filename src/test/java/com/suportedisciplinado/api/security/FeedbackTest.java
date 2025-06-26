package com.suportedisciplinado.api.security;

import com.suportedisciplinado.api.model.Feedback;
import com.suportedisciplinado.api.model.Ticket;
import com.suportedisciplinado.api.model.User;
import net.jqwik.api.*;
import org.junit.jupiter.api.Assertions;

import java.util.Date;

class FeedbackTest {

    @Provide
    Arbitrary<Integer> validRatings() {
        return Arbitraries.integers().between(1, 5);
    }

    @Provide
    Arbitrary<String> validComments() {
        return Arbitraries.strings().withCharRange('a', 'z').ofMinLength(5).ofMaxLength(300);
    }

    @Provide
    Arbitrary<User> users() {
        return Arbitraries.just(new User()); // Simples mock
    }

    @Provide
    Arbitrary<Ticket> tickets() {
        return Arbitraries.just(new Ticket()); // Simples mock
    }

    @Property
    void shouldCreateFeedbackCorrectly(@ForAll("validRatings") int rating,
                                       @ForAll("validComments") String comment,
                                       @ForAll("users") User user,
                                       @ForAll("tickets") Ticket ticket) {
        Feedback feedback = new Feedback();
        feedback.setRating(rating);
        feedback.setComment(comment);
        feedback.setUser(user);
        feedback.setTicket(ticket);
        feedback.setFeedbackAt(new Date());

        Assertions.assertEquals(rating, feedback.getRating());
        Assertions.assertEquals(comment, feedback.getComment());
        Assertions.assertEquals(user, feedback.getUser());
        Assertions.assertEquals(ticket, feedback.getTicket());
        Assertions.assertNotNull(feedback.getFeedbackAt());
    }
}