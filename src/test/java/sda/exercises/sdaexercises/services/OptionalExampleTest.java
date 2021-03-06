package sda.exercises.sdaexercises.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sda.exercises.sdaexercises.model.User;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class OptionalExampleTest {

    private OptionalExample optionalExample;

    @BeforeEach
    void setUp() {
        optionalExample = new OptionalExample();
        List.of("Marek", "Beata", "Mikołaj", "Miłosz", "Marzena", "Zosia", "Joanna", "Daniel", "Monika")
                .stream()
                .map(User::new)
                .forEach(optionalExample::add);
    }

    @Test
    void testGet() {
        optionalExample.add(new User(null));
        Integer actual = optionalExample.getById(9)
                .flatMap(this::getOptionalName)
                .map(String::length)
                .orElse(0);

        assertEquals(actual, 0);
    }

    private Optional<String> getOptionalName(User user) {
        return Optional.ofNullable(user.getName());
    }
}