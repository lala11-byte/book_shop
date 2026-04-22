package com.example.comments.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookComment {
    private Long id;
    private String isbn;
    private Long userId;
    private String content;
    private Integer rating;
    private LocalDateTime createTime;
}