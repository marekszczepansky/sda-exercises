package sda.exercises.sdaexercises.services;

import sda.exercises.sdaexercises.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    User createUser(User user);
    User getUser(Integer id);
    void deleteUser(Integer id);
    User updateUser(Integer id, User user);

}
