package com.suportedisciplinado.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "feedbacks")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "ticket_id", nullable = false, unique = true)
    private Ticket ticket;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private int rating;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @Column(name = "feedback_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date feedbackAt = new java.util.Date();

    //Getters and Setters
    public Long getId() { return id;}
    public void setId(Long id) { this.id = id;}

    public Ticket getTicket() { return ticket; }
    public void setTicket(Ticket ticket) { this.ticket = ticket; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public java.util.Date getFeedbackAt() { return feedbackAt; }
    public void setFeedbackAt(java.util.Date feedbackAt) { this.feedbackAt = feedbackAt; }

}
