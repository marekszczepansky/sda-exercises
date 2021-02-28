package sda.exercises.sdaexercises.controllers;

import org.springframework.web.bind.annotation.*;
import sda.exercises.sdaexercises.model.Comment;
import sda.exercises.sdaexercises.services.CommentService;

import java.util.List;

@RestController
@RequestMapping("posts/{postId}/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> getComments(@PathVariable Integer postId) {
        return commentService.getCommentsForPost(postId);
    }

    @PostMapping
    public Comment createComment(
            @PathVariable Integer postId,
            @RequestBody Comment comment,
            @RequestHeader Integer userId
    ) {
        return commentService.createCommentForPost(postId, userId, comment);
    }

    @GetMapping("{id}")
    public Comment getComment(@PathVariable Integer id) {
        return commentService.getComment(id);
    }

    @DeleteMapping("{id}")
    public void deleteComment(@PathVariable Integer id) {
        commentService.deleteComment(id);
    }

    @PutMapping("{id}")
    public Comment editComment(@PathVariable Integer id, @RequestBody Comment comment) {
        return commentService.editComment(id, comment);
    }
}
