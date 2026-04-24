package com.example.books.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({
        "com.example.comments",
        "com.example.books.mapper"
})
public class MybatisConfig {
}