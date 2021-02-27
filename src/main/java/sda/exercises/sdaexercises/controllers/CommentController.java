package sda.exercises.sdaexercises.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sda.exercises.sdaexercises.model.Comment;
import sda.exercises.sdaexercises.model.Post;
import sda.exercises.sdaexercises.model.User;
import sda.exercises.sdaexercises.repositories.CommentRepository;
import sda.exercises.sdaexercises.repositories.PostRepository;
import sda.exercises.sdaexercises.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("posts/{postId}/comments")
public class CommentController {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommentController(
            CommentRepository commentRepository,
            PostRepository postRepository,
            UserRepository userRepository
    ) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Comment> getComments(@PathVariable Integer postId) {
        return commentRepository.findAllByPostId(postId);
    }

    @PostMapping
    public Comment createComment(
            @PathVariable Integer postId,
            @RequestBody Comment comment,
            @RequestHeader Integer userId
    ){
        final Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        final Optional<Post> postOptional = postRepository.findById(postId);
        if (postOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        comment.setAuthor(userOptional.get());
        comment.setPost(postOptional.get());
        comment.setCreated(LocalDateTime.now());
        return commentRepository.save(comment);
    }

    @GetMapping("{id}")
    public Comment getComment(@PathVariable Integer id) {
        final Optional<Comment> commentOptional = commentRepository.findById(id);
        if (commentOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return commentOptional.get();
    }

    @DeleteMapping("{id}")
    public void deleteComment(@PathVariable Integer id) {
        commentRepository.deleteById(id);
    }
}
