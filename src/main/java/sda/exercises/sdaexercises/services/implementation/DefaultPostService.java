package sda.exercises.sdaexercises.services.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sda.exercises.sdaexercises.exceptions.EntityNotFoundException;
import sda.exercises.sdaexercises.model.Post;
import sda.exercises.sdaexercises.model.User;
import sda.exercises.sdaexercises.repositories.PostRepository;
import sda.exercises.sdaexercises.repositories.UserRepository;
import sda.exercises.sdaexercises.services.PostService;
import sda.exercises.sdaexercises.services.TimeService;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class DefaultPostService implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final TimeService timeService;

    public DefaultPostService(
            PostRepository postRepository,
            UserRepository userRepository,
            TimeService timeService
    ) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.timeService = timeService;
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
    @Transactional(readOnly = false)
    public Post createPost(Post post, Integer userId) {
        final User author = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        post.setAuthor(author);
        post.setCreated(timeService.getNow());
        return postRepository.saveAndFlush(post);
    }

    @Override
    @Transactional(readOnly = false)
    public void deletePost(Integer id) {
        postRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public Post editPost(Integer postId, Post post) {
        final Post dbPost = postRepository.findById(postId)
                .orElseThrow(DefaultPostService::getPostNotFoundException);
        dbPost.setMessage(post.getMessage());
        return postRepository.save(dbPost);
    }
}
