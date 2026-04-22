package com.example.books.service;

import java.util.Map;

public interface BookService {

    Map<String, Object> getBookList(
            String s, String keyword,
            String author,
            String publisher,
            String title,
            String isbn,
            Integer page,
            Integer size
    );
    Map<String, Object> getProfile(String isbn);
}