package sda.exercises.sdaexercises.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sda.exercises.sdaexercises.model.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findAllByPostId(Integer id);
}
