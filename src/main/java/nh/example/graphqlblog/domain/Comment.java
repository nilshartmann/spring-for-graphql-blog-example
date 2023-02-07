package nh.example.graphqlblog.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name="comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    private String comment;

    @ManyToOne
    private Post post;

    protected Comment() {}

    public Comment(Post post, String comment) {
        this.post = post;
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Comment{" +
               "id=" + id +
               ", comment='" + comment + '\'' +
               ", post=" + post.getId() +
               '}';
    }

    public Long getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public Post getPost() {
        return post;
    }
}
