package sda.exercises.sdaexercises.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String message;
    private LocalDateTime created;
    @ManyToOne
    private User author;

    public Post() {
    }

    public Post(String message, LocalDateTime created, User author) {
        this.message = message;
        this.created = created;
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public User getAuthor() {
        return author;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
