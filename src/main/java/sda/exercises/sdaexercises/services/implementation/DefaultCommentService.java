package sda.exercises.sdaexercises.services.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sda.exercises.sdaexercises.exceptions.EntityNotFoundException;
import sda.exercises.sdaexercises.exceptions.UserNotFoundException;
import sda.exercises.sdaexercises.model.Comment;
import sda.exercises.sdaexercises.model.Post;
import sda.exercises.sdaexercises.model.User;
import sda.exercises.sdaexercises.repositories.CommentRepository;
import sda.exercises.sdaexercises.repositories.PostRepository;
import sda.exercises.sdaexercises.repositories.UserRepository;
import sda.exercises.sdaexercises.services.CommentService;

import java.time.LocalDateTime;
import java.util.List;

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

    private static EntityNotFoundException getCommentNotFoundException() {
        return new EntityNotFoundException("Comment not found");
    }


    @Override
    public List<Comment> getCommentsForPost(Integer postId) {
        return commentRepository.findAllByPostId(postId);
    }

    @Override
    @Transactional(readOnly = false)
    public Comment createCommentForPost(Integer postId, Integer userId, Comment comment) {
        final User author = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        final Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));

        comment.setAuthor(author);
        comment.setPost(post);
        comment.setCreated(LocalDateTime.now());
        return commentRepository.saveAndFlush(comment);
    }

    @Override
    public Comment getComment(Integer id) {
        return commentRepository.findById(id)
                .orElseThrow(DefaultCommentService::getCommentNotFoundException);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteComment(Integer id) {
        commentRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public Comment editComment(Integer id, Comment comment) {
        Comment dbComment = commentRepository.findById(id)
                .orElseThrow(DefaultCommentService::getCommentNotFoundException);
        dbComment.setMessage(comment.getMessage());
        return commentRepository.save(dbComment);
    }
}
