package sda.exercises.sdaexercises.services.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sda.exercises.sdaexercises.model.User;
import sda.exercises.sdaexercises.services.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service("UserServiceProxy")
public class DefaultUserServiceProxy implements UserService {
    private static Logger logger = LoggerFactory.getLogger(DefaultUserServiceProxy.class);
    private final UserService userService;

    public DefaultUserServiceProxy(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<User> getUsers() {
        final List<User> users = userService.getUsers();
        return users.stream()
                .peek(user -> user.setName(user.getName() + " via proxy"))
                .collect(Collectors.toList());
    }

    @Override
    public User createUser(User user) {
        return userService.createUser(user);
    }

    @Override
    public User getUser(Integer id) {
        return userService.getUser(id);
    }

    @Override
    public void deleteUser(Integer id) {
        if (id == 3) {
            logger.warn("User id 3 cannot be deleted");
            return;
        }
        userService.deleteUser(id);
    }

    @Override
    public User updateUser(Integer id, User user) {
        return userService.updateUser(id, user);
    }
}
