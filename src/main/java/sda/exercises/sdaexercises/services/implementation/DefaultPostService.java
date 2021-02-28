package sda.exercises.sdaexercises.services.implementation;

import org.springframework.stereotype.Service;
import sda.exercises.sdaexercises.exceptions.EntityNotFoundException;
import sda.exercises.sdaexercises.exceptions.UserNotFoundException;
import sda.exercises.sdaexercises.model.Post;
import sda.exercises.sdaexercises.model.User;
import sda.exercises.sdaexercises.repositories.PostRepository;
import sda.exercises.sdaexercises.repositories.UserRepository;
import sda.exercises.sdaexercises.services.PostService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DefaultPostService implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public DefaultPostService(
            PostRepository postRepository,
            UserRepository userRepository
    ) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    private static EntityNotFoundException getPostNotFoundException() {
        return new EntityNotFoundException("Post not found");
    }

    @Override
    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post getPost(Integer postId) {
        return postRepository.findById(postId)
                .orElseThrow(DefaultPostService::getPostNotFoundException);
    }

    @Override
    public Post createPost(Post post, Integer userId) {
        final User author = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        post.setAuthor(author);
        post.setCreated(LocalDateTime.now());
        return postRepository.saveAndFlush(post);
    }

    @Override
    public void deletePost(Integer id) {
        postRepository.deleteById(id);
    }

    @Override
    public Post editPost(Integer postId, Post post) {
        final Post dbPost = postRepository.findById(postId)
                .orElseThrow(DefaultPostService::getPostNotFoundException);
        dbPost.setMessage(post.getMessage());
        return postRepository.save(dbPost);
    }
}
