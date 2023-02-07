package nh.example.graphqlblog;

import nh.example.graphqlblog.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Example;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@EnableAsync
public class SpringGraphqlJavaMagazinApplication {
private static final Logger log = LoggerFactory.getLogger( SpringGraphqlJavaMagazinApplication.class );
    public static void main(String[] args) {
        SpringApplication.run(SpringGraphqlJavaMagazinApplication.class, args);
    }
}
