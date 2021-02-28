package sda.exercises.sdaexercises.services;

import sda.exercises.sdaexercises.model.Post;

import java.util.List;

public interface PostService {
    List<Post> getPosts();
    Post getPost(Integer postId);
    Post createPost(Post post, Integer userId);
    void deletePost(Integer id);
    Post editPost(Integer postId, Post post);
}
