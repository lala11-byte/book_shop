package com.example.books.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 图书核心信息表
 * 对应数据库表：books
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private String isbn;
    private String title;
    private String subtitle;
    private String author;
    private String publisher;
    private String image;
    private String description;
    private LocalDateTime publishDate;
    private Integer stock; // ✅ 新增在售数量
}