package nh.example.graphqlblog.domain;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public PostService(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Post addPost(Long userId, String title, String body) {
        User user = userRepository.findById(1L).orElseThrow();
        Post post = new Post(user, title, body);
        postRepository.save(post);

        return post;
    }
}
