package nh.example.graphqlblog.graphql;

import nh.example.graphqlblog.domain.IllegalPostDataException;
import nh.example.graphqlblog.domain.Post;
import nh.example.graphqlblog.domain.PostRepository;
import nh.example.graphqlblog.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static java.lang.Math.max;
import static java.lang.Math.min;

@Service
public class BlogService {

    private static final Logger log = LoggerFactory.getLogger( BlogService.class );

    private final PostRepository postRepository;

    public BlogService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    // Demo: asynchrone Verarbeitung in Handler-Methoden
    @Async("chatGPTExecutor")
    public CompletableFuture<String> buildSummaryWithChatGPT(String body, int maxWords) {

        log.info("Determining summary... {}", Thread.currentThread().getName());

        // Calculating the summary takes some time 🙁...
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // ...and it's not even ChatGPT though 😢...
        var words = Arrays.stream(body.split(" "))
            .limit(maxWords)
            .collect(Collectors.joining(" "));

        return CompletableFuture.completedFuture(words);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Post addPost(User user, String title, String body) throws IllegalPostDataException {

        if (title.length() < 5) {
            throw new IllegalPostDataException("title", "Length should be at least five chars");
        }

        if (body.length() < 10) {
            throw new IllegalPostDataException("body", "Length should be at least ten chars");
        }

        var newPost = new Post(user, title, body);

        postRepository.save(newPost);

        return newPost;
    }

}
