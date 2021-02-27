package sda.exercises.sdaexercises.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sda.exercises.sdaexercises.model.Post;

public interface PostRepository extends JpaRepository <Post, Integer> {
}
