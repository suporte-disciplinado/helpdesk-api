package com.suportedisciplinado.api;

import com.suportedisciplinado.api.arbitraries.CustomArbitraries;
import com.suportedisciplinado.api.model.*;

import net.jqwik.api.*;
import net.jqwik.api.constraints.*;
import org.junit.jupiter.api.Assertions;

import java.util.Date;

class FeedbackTest {
    
  @Provide
  Arbitrary<Integer> validRatings() {
      return Arbitraries.integers().between(1, 5);
  }
  
  @Provide
  Arbitrary<String> validComments() {
      return Arbitraries.strings().withCharRange('a', 'z').ofMinLength(5).ofMaxLength(500);
  }

  @Provide
  Arbitrary<User> validUsers() {
        return CustomArbitraries.validUser();
    }

  @Provide
  Arbitrary<Ticket> validTickets() {
      return CustomArbitraries.validTicket();
  }
  
  @Property
  void testFeedbackCreation(@ForAll("validRatings") int rating, @ForAll("validComments") String comment, @ForAll("validUsers") User user, @ForAll("validTickets") Ticket ticket) {
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
