package sda.exercises.sdaexercises.controllers;

import org.springframework.web.bind.annotation.*;
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

    @GetMapping
    public List<Post> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("{id}")
    public Post getPost(@PathVariable Integer id) {
        return postService.getPost(id);
    }

    @PostMapping
    public Post createPost(@RequestBody Post post, @RequestHeader Integer userId) {
        return postService.createPost(post, userId);
    }

    @DeleteMapping("{id}")
    public void deletePost(@PathVariable int id) {
        postService.deletePost(id);
    }

    @PutMapping("{id}")
    public Post editPost(@PathVariable Integer id, @RequestBody Post post) {
        return postService.editPost(id, post);
    }
}
