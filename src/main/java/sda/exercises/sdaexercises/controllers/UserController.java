package sda.exercises.sdaexercises.controllers;

import org.springframework.web.bind.annotation.*;
import sda.exercises.sdaexercises.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * localhost:8080/users - lista użytkowników
 * localhost:8080/users/1 - użytkownik o id 1
 */
@RestController
@RequestMapping("users")
public class UserController {

    private List<User> userList = new ArrayList<>();

    @GetMapping
    public List<User> getUsers(){
        return userList;
    };

    @GetMapping("{id}")
    public User getUser(@PathVariable Integer id){
        return userList.get(id);
    }

    @PostMapping
    public User createUser(@RequestParam String name){
        final User user = new User(name);
        user.setId(userList.size());
        userList.add(user);
        return user;
    };

}
