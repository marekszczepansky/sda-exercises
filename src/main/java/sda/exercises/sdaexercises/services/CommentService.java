package sda.exercises.sdaexercises.services;

import sda.exercises.sdaexercises.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<Comment> getCommentsForPost(Integer postId);
    Comment createCommentForPost(Integer postId, Integer userId, Comment comment);
    Optional<Comment> getComment(Integer id);
    void deleteComment(Integer id);
    Optional<Comment> editComment(Integer id, Comment comment);
}
