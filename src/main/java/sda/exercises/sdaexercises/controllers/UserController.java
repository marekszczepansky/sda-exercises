package sda.exercises.sdaexercises.controllers;

import org.springframework.web.bind.annotation.*;
import sda.exercises.sdaexercises.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * localhost:8080/users - lista użytkowników, POST
 * localhost:8080/users/1 - użytkownik o id 1 - GET, PUT, DELETE
 */
@RestController
@RequestMapping("users")
public class UserController {

    private List<User> userList = new ArrayList<>();

    @GetMapping
    public List<User> getUsers() {
        return userList;
    }

    @GetMapping("{id}")
    public User getUser(@PathVariable Integer id) {
        return userList.get(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        user.setId(userList.size());
        userList.add(user);
        return user;
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable int id) {
        userList.remove(id);
    }

    @PutMapping("{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody User user) {
        user.setId(id);
        userList.set(id, user);
        return user;
    }

}
