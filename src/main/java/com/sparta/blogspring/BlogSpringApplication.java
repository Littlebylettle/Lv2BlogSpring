package com.sparta.blogspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BlogSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogSpringApplication.class, args);
    }

}
