package com.suportedisciplinado.api.controller;

import com.suportedisciplinado.api.model.TicketComment;
import com.suportedisciplinado.api.model.User;
import com.suportedisciplinado.api.security.CustomUserDetails;
import com.suportedisciplinado.api.service.TicketCommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class TicketCommentController
{
    private final TicketCommentService commentService;

    public TicketCommentController(TicketCommentService commentService)
    {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<String> createComment(@RequestBody TicketComment newComment, @AuthenticationPrincipal CustomUserDetails userDetails)
    {
        User user = userDetails.getUser();
        newComment.setUser(user);

        return commentService.createComment(newComment);
    }

    @PutMapping
    public ResponseEntity<String> updateComment(@RequestBody TicketComment updatedComment)
    {
        return commentService.updateComment(updatedComment);
    }

    @GetMapping
    public ResponseEntity<List<TicketComment>> searchForAllComments( @RequestParam(required = false) Long idTicket)
    {
        if (idTicket != null) {
            return commentService.getAllCommentsByTicketId(idTicket);
        } else {
            return commentService.getAllComments();
        }
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<TicketComment> searchForComment (@PathVariable Long commentId)
    {
        return commentService.getCommentById(commentId);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId)
    {
        return commentService.deleteCommentById(commentId);
    }
}
