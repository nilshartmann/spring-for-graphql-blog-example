package nh.example.graphqlblog.graphql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static java.lang.Math.max;
import static java.lang.Math.min;

@Service
public class BlogService {

    private static final Logger log = LoggerFactory.getLogger( BlogService.class );

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

}
