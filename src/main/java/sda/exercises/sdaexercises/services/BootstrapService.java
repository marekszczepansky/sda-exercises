package sda.exercises.sdaexercises.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sda.exercises.sdaexercises.model.User;

import javax.annotation.PostConstruct;

@Service
public class BootstrapService {
    private final UserService userService;

    public BootstrapService(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    @Transactional
    void createAdminUser(){
        final int usersCount = userService.getUsers().size();
        if (usersCount == 0) {
            userService.createUser(new User("Admin"));
        }
    }
}
