package sda.exercises.sdaexercises.services.implementation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sda.exercises.sdaexercises.model.Comment;
import sda.exercises.sdaexercises.model.Post;
import sda.exercises.sdaexercises.model.User;
import sda.exercises.sdaexercises.repositories.CommentRepository;
import sda.exercises.sdaexercises.repositories.PostRepository;
import sda.exercises.sdaexercises.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultCommentServiceTest {

    @Mock
    private CommentRepository commentRepository;
    @Mock
    private PostRepository postRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private DefaultCommentService commentService;

    @Test
    void shouldCreateCommentForPost() {
        User testPostAuthor = new User("Test author name");
        Integer testPostId = 23;
        LocalDateTime postCreated = LocalDateTime.of(2021,2,3,4,5);
        Post testPost = new Post("Test post message", postCreated, testPostAuthor);

        Integer testCommentAuthorId = 12;
        User commentAuthor = new User("Test comment author");
        LocalDateTime commentCreated = LocalDateTime.of(2021, 5,6,7,8);
        Comment testComment = new Comment("Test comment message", null, null, null);
        commentAuthor.setId(testCommentAuthorId);
        Integer newCommentId = 45;

        testPostAuthor.setId(testCommentAuthorId);
        when(userRepository.findById(testCommentAuthorId))
                .thenReturn(Optional.of(commentAuthor));
        when(postRepository.findById(testPostId))
                .thenReturn(Optional.of(testPost));
        when(commentRepository.saveAndFlush(any(Comment.class)))
                .thenAnswer(invocation -> {
                    final Comment comment = invocation.getArgument(0, Comment.class);
                    comment.setId(newCommentId);
                    return comment;
                });

        final Comment actualComment = commentService
                .createCommentForPost(testPostId, testCommentAuthorId, testComment);


        assertEquals(newCommentId, actualComment.getId());
        assertEquals(commentAuthor, actualComment.getAuthor());
        assertEquals(testPost, actualComment.getPost());

    }
}