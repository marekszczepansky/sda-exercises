package sda.exercises.sdaexercises.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sda.exercises.sdaexercises.model.Post;
import sda.exercises.sdaexercises.model.User;
import sda.exercises.sdaexercises.repositories.PostRepository;
import sda.exercises.sdaexercises.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("posts")
public class PostController {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostController(
            PostRepository postRepository,
            UserRepository userRepository
    ) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    @GetMapping("{id}")
    public Post getPost(@PathVariable Integer id) {
        return postRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Post createPost(@RequestBody Post post, @RequestHeader Integer userId) {
        final Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        post.setAuthor(optionalUser.get());
        post.setCreated(LocalDateTime.now());
        return postRepository.saveAndFlush(post);
    }

    @DeleteMapping("{id}")
    public void deletePost(@PathVariable int id) {
        postRepository.deleteById(id);
    }

    @PutMapping("{id}")
    public Post editPost(@PathVariable Integer id, @RequestBody Post post) {
        post.setId(id);
        return postRepository.saveAndFlush(post);
    }
}
