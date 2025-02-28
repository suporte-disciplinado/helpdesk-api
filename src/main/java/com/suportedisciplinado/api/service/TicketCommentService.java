package com.suportedisciplinado.api.service;

import com.suportedisciplinado.api.model.Ticket;
import com.suportedisciplinado.api.model.TicketComment;
import com.suportedisciplinado.api.repository.TicketCommentRepository;
import com.suportedisciplinado.api.repository.TicketRepository;
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

    public TicketComment getCommentById(Long commentId)
    throws NullPointerException
    {
        Objects.requireNonNull(commentId, "O id do comentário informado está nulo, por favor informe um id válido!");

        TicketComment comment = commentRepository.getOne(commentId);
        validateComment(comment);
        return comment;
    }

    public void updateComment(TicketComment updatedComment)
    throws NullPointerException
    {
        validateComment(updatedComment);

        TicketComment commentToUpdate = getCommentById(updatedComment.getId());
        User user = userRepository.getOne(updatedComment.getUser().getId());
        Ticket ticket = ticketRepository.getOne(updatedComment.getTicket().getId());

        commentToUpdate.setComment(updatedComment.getComment());
        commentToUpdate.setTicket(ticket);
        commentToUpdate.setUser(user);

        commentRepository.save(commentToUpdate);
    }

    public List<TicketComment> getAllComments() {
        return commentRepository.findAll();
    }

    public void deleteCommentById(Long id) {
        commentRepository.deleteById(id);
    }

    public void createComment(TicketComment newComment) {
        validateComment(newComment);

        User user = userRepository.getOne(newComment.getUser().getId());
        Ticket ticket = ticketRepository.getOne(newComment.getTicket().getId());

        newComment.setTicket(ticket);
        newComment.setUser(user);

        commentRepository.saveAndFlush(newComment);
    }

    private void validateComment(TicketComment comment)
    throws NullPointerException
    {
        Objects.requireNonNull(comment, "O comentário não pode ser nulo, por favor forneca um comentário válido!");
    }
}
