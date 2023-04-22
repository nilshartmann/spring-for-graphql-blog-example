package nh.example.graphqlblog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringGraphqlBlogApplication {
private static final Logger log = LoggerFactory.getLogger( SpringGraphqlBlogApplication.class );
    public static void main(String[] args) {
        SpringApplication.run(SpringGraphqlBlogApplication.class, args);
    }
}
