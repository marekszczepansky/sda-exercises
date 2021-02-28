package sda.exercises.sdaexercises.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sda.exercises.sdaexercises.exceptions.EntityNotFoundException;
import sda.exercises.sdaexercises.model.Comment;
import sda.exercises.sdaexercises.services.CommentService;

import java.util.List;
import java.util.Optional;

import static sda.exercises.sdaexercises.exceptions.EntityNotFoundException.EntityType.BUSINESS;

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
        try {
            return commentService.createCommentForPost(postId, userId, comment);
        } catch (EntityNotFoundException e) {
            if (e.getEntityType() == BUSINESS) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("{id}")
    public Comment getComment(@PathVariable Integer id) {
        final Optional<Comment> commentOptional = commentService.getComment(id);
        if (commentOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return commentOptional.get();
    }

    @DeleteMapping("{id}")
    public void deleteComment(@PathVariable Integer id) {
        commentService.deleteComment(id);
    }

    @PutMapping("{id}")
    public Comment editComment(@PathVariable Integer id, @RequestBody Comment comment) {
        final Optional<Comment> commentOptional = commentService.editComment(id, comment);
        if (commentOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return commentOptional.get();
    }
}
