package com.example.books.controller.impl;

import com.example.books.controller.BookController;

import com.example.comments.mapper.BookCommentMapper;
import com.example.common.Result;
import com.example.books.service.BookService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookControllerImpl implements BookController {

    @Resource
    private BookService bookService;
    @Resource
    private BookCommentMapper bookCommentMapper;

    @Override
    @GetMapping
    public Result getBooks(
            String keyword,
            String type,
            String author,
            String publisher,
            String title,
            String isbn,
            Integer page,
            Integer size
    ) {
        return Result.success(
                bookService.getBookList(keyword, type, author, publisher, title, isbn, page, size)
        );

    }
    @GetMapping("book-profile/{isbn}")
    public Result getProfile(@PathVariable String isbn){
        return Result.success(bookService.getProfile(isbn));
    }

}