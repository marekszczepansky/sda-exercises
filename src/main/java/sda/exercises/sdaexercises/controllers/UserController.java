package sda.exercises.sdaexercises.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sda.exercises.sdaexercises.exceptions.EntityNotFoundException;
import sda.exercises.sdaexercises.model.User;
import sda.exercises.sdaexercises.services.UserService;

import java.util.List;
import java.util.Optional;

/**
 * localhost:8080/users - lista użytkowników, POST
 * localhost:8080/users/1 - użytkownik o id 1 - GET, PUT, DELETE
 */
@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("{id}")
    public User getUser(@PathVariable Integer id) {
        return userService.getUser(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }

    @PutMapping("{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }
}
