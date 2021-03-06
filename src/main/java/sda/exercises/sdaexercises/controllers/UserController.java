package sda.exercises.sdaexercises.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sda.exercises.sdaexercises.controllers.security.UserGuard;
import sda.exercises.sdaexercises.model.User;
import sda.exercises.sdaexercises.services.UserService;

import java.util.List;

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

    @UserGuard
    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @UserGuard
    @GetMapping("{id}")
    public User getUser(@PathVariable Integer id) {
        return userService.getUser(id);
    }

    @UserGuard
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @UserGuard
    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }

    @UserGuard
    @PutMapping("{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }
}
