package com.example.books.service.impl;

import com.example.comments.pojo.BookComment;
import com.example.books.mapper.BookMapper;
import com.example.books.pojo.Book;
import com.example.books.service.BookService;
import jakarta.annotation.Resource;
import com.example.comments.mapper.BookCommentMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService {

    @Resource
    private BookMapper bookMapper;
    @Resource
    private BookCommentMapper bookCommentMapper;
    @Override
    public Map<String, Object> getBookList(
            String keyword,
            String type,
            String author,
            String publisher,
            String title,
            String isbn,
            Integer page,
            Integer size
    ) {
        Integer offset = null;

        if(page!=null)
        { offset = (page - 1) * size;}

        List<Book> list = bookMapper.selectBooks(
                keyword, type, author, publisher, title, isbn, offset, size
        );

        int total = bookMapper.countBooks(
                keyword, type, author, publisher, title, isbn
        );

        Map<String, Object> data = new HashMap<>();
        data.put("records", list);
        data.put("total", total);
        data.put("page", page);
        data.put("size", size);

        return data;
    }

    public Map<String, Object> getProfile(String isbn)
    {
        List<BookComment>comments=bookCommentMapper.selectComment(isbn);
        Book book=bookMapper.selectByIsbn(isbn);
        Map<String,Object> data=new HashMap<>();
        data.put("book",book);
        data.put("comments",comments);
        return data;
    }
}