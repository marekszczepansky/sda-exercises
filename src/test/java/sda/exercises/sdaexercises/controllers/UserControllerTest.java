package sda.exercises.sdaexercises.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import sda.exercises.sdaexercises.exceptions.EntityNotFoundException;
import sda.exercises.sdaexercises.model.User;
import sda.exercises.sdaexercises.services.UserService;

import java.util.List;

import static java.lang.String.format;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void shouldGetUsers() throws Exception {
        when(userService.getUsers()).thenReturn(List.of(
                new User("Test 1"), new User("Test 2")
        ));

        mockMvc.perform(get("/users").header("userId", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Test 1")))
                .andExpect(jsonPath("$[1].name", is("Test 2")));
    }

    @Test
    void shouldNotGetUsersForUndefinedUserId() throws Exception {
        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldNotGetUsersForUnknownUserId() throws Exception {
        when(userService.getUser(1)).thenThrow(EntityNotFoundException.class);

        mockMvc.perform(get("/users").header("userId", "1"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldCreateNewUser() throws Exception {
        final String newUserName = "Test new user 1";
        final Integer newUserId = 12;
        final User newUser = new User(newUserName);
        newUser.setId(newUserId);
        when(userService.createUser(any(User.class))).thenReturn(newUser);

        mockMvc.perform(
                post("/users")
                        .header("userId", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(format("{ \"name\" : \"%s\" }", newUserName)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(newUserId)))
                .andExpect(jsonPath("$.name", is(newUserName)));
    }


}