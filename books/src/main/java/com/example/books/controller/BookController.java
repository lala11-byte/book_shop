package com.example.books.controller;


import com.example.common.Result;

public interface BookController {
    Result getBooks(String keyword, String type, String author,
                    String publisher, String title, String isbn,
                    Integer page, Integer size);
    Result getProfile(String isbn);
}