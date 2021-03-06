package sda.exercises.sdaexercises.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import sda.exercises.sdaexercises.model.Post;
import sda.exercises.sdaexercises.model.User;
import sda.exercises.sdaexercises.services.PostService;
import sda.exercises.sdaexercises.services.UserService;

import java.time.LocalDateTime;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.hamcrest.Matchers.is;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private PostService postService;

    @Test
    void shouldCreatePost() throws Exception {
        final String postMessage = "Test post message";
        final String postContent = format("{ \"message\": \"%s\" }", postMessage);
        final int userId = 12;
        final LocalDateTime testCreated = LocalDateTime.of(2021, 02, 03, 04, 05);
        final String createdJson = "2021-02-03T04:05:00";
        final String testUserName = "test user name";
        final User testUser = new User(testUserName);
        testUser.setId(userId);
        final Post testPost = new Post(postMessage, testCreated, testUser);
        final int testPostId = 5;
        testPost.setId(testPostId);
        testPost.setAuthor(testUser);

        when(postService.createPost(any(Post.class), eq(userId)))
                .thenReturn(testPost);

        mockMvc.perform(
                post("/posts")
                        .header("userId", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postContent))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(testPostId)))
                .andExpect(jsonPath("$.message", is(postMessage)))
                .andExpect(jsonPath("$.created", is(createdJson)))
                .andExpect(jsonPath("$.author.id", is(userId)))
                .andExpect(jsonPath("$.author.name", is(testUserName)))
        ;
    }
}