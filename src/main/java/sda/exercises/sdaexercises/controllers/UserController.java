package sda.exercises.sdaexercises.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sda.exercises.sdaexercises.model.User;

import java.util.List;

/**
 * localhost:8080/users - lista użytkowników
 * localhost:8080/users/1 - użytkownik o id 1
 */
@RestController
@RequestMapping("users")
public class UserController {

    @GetMapping
    public List<User> getUsers(){
        return List.of(
          new User("Marek"),
          new User("Tomek")
        );
    };

}
