package com.suportedisciplinado.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Check;

import java.time.LocalDateTime;

@Entity
@Table(name="ticket_attachments")
@Getter
@Setter
@NoArgsConstructor
@Check(constraints = "(ticket_id IS NOT NULL AND comment_id IS NULL) or (ticket_id IS NULL and comment_id IS NOT NULL)")
public class TicketAttachment
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    @JsonBackReference("ticket-attachment")
    private Ticket ticket;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    @JsonBackReference("comment-attachment")
    private TicketComment comment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String filePath;

    @Column(nullable = false, length = 50)
    private String fileType;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column
    private LocalDateTime updatedAt;

    @Column
    private LocalDateTime deletedAt;

    @PreUpdate
    public void setUpdatedAt()
    {
        this.updatedAt = LocalDateTime.now();
    }
}
