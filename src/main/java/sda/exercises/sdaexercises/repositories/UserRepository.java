package sda.exercises.sdaexercises.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sda.exercises.sdaexercises.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
