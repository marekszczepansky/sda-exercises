package sda.exercises.sdaexercises.services.implementation;

import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sda.exercises.sdaexercises.exceptions.ExerciseException;
import sda.exercises.sdaexercises.exceptions.ExerciseExceptionFactory;
import sda.exercises.sdaexercises.model.User;
import sda.exercises.sdaexercises.repositories.UserRepository;
import sda.exercises.sdaexercises.services.UserService;

import java.util.List;

@Service
@Primary
@Transactional(readOnly = true)
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    public DefaultUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private static ExerciseException getUserNotFoundException() {
        return ExerciseExceptionFactory.createEntityNotFound("User not found");
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
                .orElseThrow(DefaultUserService::getUserNotFoundException);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteUser(Integer id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw ExerciseExceptionFactory.createEntityNotFound("User does not exist");
        }
    }

    @Override
    @Transactional(readOnly = false)
    public User updateUser(Integer id, User user) {
        User dbUser = userRepository.findById(id)
                .orElseThrow(DefaultUserService::getUserNotFoundException);
        dbUser.setName(user.getName());
        return userRepository.save(dbUser);
    }
}
