package com.suportedisciplinado.api.service;

import com.suportedisciplinado.api.model.Ticket;
import com.suportedisciplinado.api.model.TicketComment;
import com.suportedisciplinado.api.model.User;
import com.suportedisciplinado.api.repository.TicketCommentRepository;
import com.suportedisciplinado.api.repository.TicketRepository;
import com.suportedisciplinado.api.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TicketCommentService
{
    private final TicketCommentRepository commentRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public TicketCommentService(
        TicketCommentRepository commentRepository,
        UserRepository userRepository,
        TicketRepository ticketRepository
    )
    {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.ticketRepository = ticketRepository;
    }

    public ResponseEntity<TicketComment> getCommentById(Long commentId)
    throws NullPointerException
    {
        Objects.requireNonNull(commentId, "The comment id informed is null, please pass a valid id!");

        TicketComment comment = commentRepository.getOne(commentId);
        validateComment(comment);
        return ResponseEntity.ok(comment);
    }

    public ResponseEntity<String> updateComment(TicketComment updatedComment)
    throws NullPointerException
    {
        validateComment(updatedComment);

        TicketComment commentToUpdate = getCommentById(updatedComment.getId()).getBody();
        User user = userRepository.getOne(updatedComment.getUser().getId());
        Ticket ticket = ticketRepository.getOne(updatedComment.getTicket().getId());

        commentToUpdate.setComment(updatedComment.getComment());
        commentToUpdate.setTicket(ticket);
        commentToUpdate.setUser(user);

        commentRepository.save(commentToUpdate);
        return ResponseEntity.ok("Comment updated successfully!");
    }

    public ResponseEntity<List<TicketComment>> getAllComments() {
        return ResponseEntity.ok(commentRepository.findAll());
    }

    public ResponseEntity<String> deleteCommentById(Long id) {
        commentRepository.deleteById(id);
        return ResponseEntity.ok("Comment deleted successfully!");
    }

    public ResponseEntity<String> createComment(TicketComment newComment) {
        validateComment(newComment);

        User user = userRepository.getOne(newComment.getUser().getId());
        Ticket ticket = ticketRepository.getOne(newComment.getTicket().getId());

        newComment.setTicket(ticket);
        newComment.setUser(user);

        commentRepository.saveAndFlush(newComment);
        return ResponseEntity.ok("Comment created successfully!");
    }

    private void validateComment(TicketComment comment)
    throws NullPointerException
    {
        Objects.requireNonNull(comment, "The comment informed is null, please pass a valid comment!");
    }
}
