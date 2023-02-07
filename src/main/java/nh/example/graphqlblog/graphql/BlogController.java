package nh.example.graphqlblog.graphql;

import nh.example.graphqlblog.domain.Post;
import nh.example.graphqlblog.domain.PostRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static java.lang.Math.min;

@Controller
public class BlogController {

    private final PostRepository postRepository;
    private final BlogService blogService;

    public BlogController(PostRepository postRepository, BlogService blogService) {
        this.postRepository = postRepository;
        this.blogService = blogService;
    }

    @QueryMapping
    public Optional<Post> post(@Argument Long postId) {
        return postRepository.findById(postId);
    }

    @QueryMapping
    public List<Post> posts() {
        return postRepository.findAll();
    }

    @SchemaMapping
    public CompletableFuture<String> summary(Post post, @Argument int maxWords) {
        var body = post.getBody();

        return blogService.buildSummaryWithChatGPT(body, maxWords);
    }
}
