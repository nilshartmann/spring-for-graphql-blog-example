package nh.example.graphqlblog.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
