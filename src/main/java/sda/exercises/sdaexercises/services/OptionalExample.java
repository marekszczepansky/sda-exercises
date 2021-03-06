package sda.exercises.sdaexercises.services;

import org.springframework.stereotype.Service;
import sda.exercises.sdaexercises.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OptionalExample {

    List<User> users = new ArrayList<>();

    public void add(User user){
        users.add(user);
        user.setId(users.size());
    }

    public Optional<User> getById(int id) {
        if (id > users.size()) {
            return Optional.empty();
        }

        return Optional.of(users.get(id));
    }

    public List<User> getAllByNameLike(String nameLike) {
        return users.stream()
                .filter(user -> user.getName().startsWith(nameLike))
                .collect(Collectors.toList());

    }
}
