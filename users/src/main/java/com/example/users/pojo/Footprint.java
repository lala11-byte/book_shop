package com.example.users.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Footprint {
    private Integer id;
    private Integer userId;
    private String isbn;
    private LocalDateTime createdAt;
}