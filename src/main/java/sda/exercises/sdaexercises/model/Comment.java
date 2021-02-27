package sda.exercises.sdaexercises.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String message;
    private LocalDateTime created;
    @ManyToOne
    private User author;
    @ManyToOne
    private Post post;

    public Comment() {
    }

    public Comment(String message, LocalDateTime created, User author, Post post) {
        this.message = message;
        this.created = created;
        this.author = author;
        this.post = post;
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

    public Post getPost() {
        return post;
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

    public void setPost(Post post) {
        this.post = post;
    }
}
