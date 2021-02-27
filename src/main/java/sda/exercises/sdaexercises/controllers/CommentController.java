package sda.exercises.sdaexercises.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sda.exercises.sdaexercises.model.Comment;
import sda.exercises.sdaexercises.repositories.CommentRepository;

import java.util.List;

@RestController
@RequestMapping("posts/{postId}/comments")
public class CommentController {
    private final CommentRepository commentRepository;

    public CommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @GetMapping
    public List<Comment> getComments(@PathVariable Integer postId) {
        return commentRepository.findAllByPostId(postId);
    }
}
