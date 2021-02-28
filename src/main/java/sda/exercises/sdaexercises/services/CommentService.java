package sda.exercises.sdaexercises.services;

import sda.exercises.sdaexercises.model.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentsForPost(Integer postId);
    Comment createCommentForPost(Integer postId, Integer userId, Comment comment);
    Comment getComment(Integer id);
    void deleteComment(Integer id);
    Comment editComment(Integer id, Comment comment);
}
