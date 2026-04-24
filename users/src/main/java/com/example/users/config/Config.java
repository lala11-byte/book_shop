package com.example.users.config;

import com.example.common.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan("com.example.common")
public class Config {

    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }

}