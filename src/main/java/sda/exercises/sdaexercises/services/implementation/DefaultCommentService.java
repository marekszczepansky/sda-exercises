package sda.exercises.sdaexercises.services.implementation;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import sda.exercises.sdaexercises.exceptions.EntityNotFoundException;
import sda.exercises.sdaexercises.model.Comment;
import sda.exercises.sdaexercises.model.Post;
import sda.exercises.sdaexercises.model.User;
import sda.exercises.sdaexercises.repositories.CommentRepository;
import sda.exercises.sdaexercises.repositories.PostRepository;
import sda.exercises.sdaexercises.repositories.UserRepository;
import sda.exercises.sdaexercises.services.CommentService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static sda.exercises.sdaexercises.exceptions.EntityNotFoundException.EntityType.BUSINESS;
import static sda.exercises.sdaexercises.exceptions.EntityNotFoundException.EntityType.SECURITY;

@Transactional(readOnly = true)
@Service
public class DefaultCommentService implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public DefaultCommentService(
            CommentRepository commentRepository,
            PostRepository postRepository,
            UserRepository userRepository
    ) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<Comment> getCommentsForPost(Integer postId) {
        return commentRepository.findAllByPostId(postId);
    }

    @Override
    @Transactional(readOnly = false)
    public Comment createCommentForPost(Integer postId, Integer userId, Comment comment) {
        final Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new EntityNotFoundException("User not found", SECURITY);
        }
        final Optional<Post> postOptional = postRepository.findById(postId);
        if (postOptional.isEmpty()) {
            throw new EntityNotFoundException("Post not found", BUSINESS);
        }

        comment.setAuthor(userOptional.get());
        comment.setPost(postOptional.get());
        comment.setCreated(LocalDateTime.now());
        return commentRepository.saveAndFlush(comment);
    }

    @Override
    public Optional<Comment> getComment(Integer id) {
        return commentRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteComment(Integer id) {
        commentRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public Optional<Comment> editComment(Integer id, Comment comment) {
        final String newMessage = comment.getMessage();
        final Optional<Comment> commentOptional = commentRepository.findById(id);
        if (commentOptional.isEmpty()) return commentOptional;
        comment = commentOptional.get();
        comment.setMessage(newMessage);
        return Optional.of(commentRepository.save(comment));
    }
}
