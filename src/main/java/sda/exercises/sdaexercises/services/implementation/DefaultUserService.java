package sda.exercises.sdaexercises.services.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sda.exercises.sdaexercises.exceptions.EntityNotFoundException;
import sda.exercises.sdaexercises.model.User;
import sda.exercises.sdaexercises.repositories.UserRepository;
import sda.exercises.sdaexercises.services.UserService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    public DefaultUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private static EntityNotFoundException getUserNotFoundExceptoin() {
        return new EntityNotFoundException("User not found");
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = false)
    public User createUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User getUser(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(DefaultUserService::getUserNotFoundExceptoin);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(Integer id, User user) {
        User dbUser = userRepository.findById(id)
                .orElseThrow(DefaultUserService::getUserNotFoundExceptoin);
        dbUser.setName(user.getName());
        return userRepository.save(dbUser);
    }
}
