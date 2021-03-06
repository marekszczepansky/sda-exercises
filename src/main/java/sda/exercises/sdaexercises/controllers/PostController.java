package sda.exercises.sdaexercises.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sda.exercises.sdaexercises.controllers.security.UserGuard;
import sda.exercises.sdaexercises.model.Post;
import sda.exercises.sdaexercises.services.PostService;

import java.util.List;

@RestController
@RequestMapping("posts")
public class PostController {

    private final PostService postService;

    public PostController(
            PostService postService
    ) {
        this.postService = postService;
    }

    @UserGuard
    @GetMapping
    public List<Post> getPosts() {
        return postService.getPosts();
    }

    @UserGuard
    @GetMapping("{id}")
    public Post getPost(@PathVariable Integer id) {
        return postService.getPost(id);
    }

    @UserGuard
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(@RequestBody Post post, @RequestHeader Integer userId) {
        final Post post1 = postService.createPost(post, userId);
        return post1;
    }

    @UserGuard
    @DeleteMapping("{id}")
    public void deletePost(@PathVariable int id) {
        postService.deletePost(id);
    }

    @UserGuard
    @PutMapping("{id}")
    public Post editPost(@PathVariable Integer id, @RequestBody Post post) {
        return postService.editPost(id, post);
    }
}
