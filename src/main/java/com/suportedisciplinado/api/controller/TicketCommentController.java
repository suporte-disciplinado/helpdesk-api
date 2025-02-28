package com.suportedisciplinado.api.controller;

import com.suportedisciplinado.api.model.TicketComment;
import com.suportedisciplinado.api.service.TicketCommentService;
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

    @PostMapping("/register/comment")
    public void createComment(@RequestBody TicketComment newComment)
    {
        commentService.createComment(newComment);
    }

    @PostMapping("/update/comment")
    public void updateComment(@RequestBody TicketComment updatedComment)
    {
        commentService.updateComment(updatedComment);
    }

    @GetMapping("/search/comments")
    public List<TicketComment> searchForAllComments()
    {
        return commentService.getAllComments();
    }

    @GetMapping("/search/comment/{commentId}")
    public @ResponseBody TicketComment searchForComment (@PathVariable Long commentId)
    {
        return commentService.getCommentById(commentId);
    }

    @DeleteMapping("/delete/comment/{commentId}")
    public void deleteComment(@PathVariable Long commentId)
    {
        commentService.deleteCommentById(commentId);
    }
}
