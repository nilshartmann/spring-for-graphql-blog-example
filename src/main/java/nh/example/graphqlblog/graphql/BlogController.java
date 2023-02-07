package nh.example.graphqlblog.graphql;

import nh.example.graphqlblog.domain.IllegalPostDataException;
import nh.example.graphqlblog.domain.Post;
import nh.example.graphqlblog.domain.PostRepository;
import nh.example.graphqlblog.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static java.lang.Math.min;

@Controller
public class BlogController {

    private static final Logger log = LoggerFactory.getLogger( BlogController.class );

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

    record AddPostInput(String title, String body) {}

    interface AddPostResult {}
    record AddPostSuccess(Post newPost) implements AddPostResult {}
    record AddPostError(String fieldName, String errorMsg) implements AddPostResult {}

    @MutationMapping
    @PreAuthorize("hasRole('ROLE_PUBLISHER')")
    public AddPostResult addPost(@Argument AddPostInput input, @AuthenticationPrincipal User user) {
        try {
            var newPost = blogService.addPost(user, input.title(), input.body());
            return new AddPostSuccess(newPost);
        } catch (IllegalPostDataException e) {
            return new AddPostError(e.getFieldName(), e.getMsg());
        }
    }
}


