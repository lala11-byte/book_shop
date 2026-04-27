package com.example.comments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(com.example.common.WebMvcConfig.class)
public class CommentsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommentsApplication.class, args);
    }

}
