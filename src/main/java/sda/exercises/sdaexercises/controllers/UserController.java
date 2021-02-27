package sda.exercises.sdaexercises.controllers;

import org.springframework.web.bind.annotation.*;
import sda.exercises.sdaexercises.model.User;
import sda.exercises.sdaexercises.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * localhost:8080/users - lista użytkowników, POST
 * localhost:8080/users/1 - użytkownik o id 1 - GET, PUT, DELETE
 */
@RestController
@RequestMapping("users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("{id}")
    public User getUser(@PathVariable Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.saveAndFlush(user);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    @PutMapping("{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody User user) {
        user.setId(id);
        return userRepository.saveAndFlush(user);
    }

}
