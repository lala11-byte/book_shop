package com.example.books.service.impl;

import com.example.books.mapper.BookMapper;
import com.example.books.mapper.UsedBookMapper;
import com.example.books.pojo.Book;
import com.example.books.pojo.UsedBook;
import com.example.books.service.UsedBookService;
import jakarta.annotation.Resource;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.math.BigDecimal;
import java.util.Map;

@Service
public class UsedBookServiceImpl implements UsedBookService {
    @Resource
    private UsedBookMapper usedBookMapper;
    @Resource
    private BookMapper bookMapper;
    @Override
    public Map<String, Object> getBookDetail(
            String isbn,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            Integer page,
            Integer pageSize) {
    Book book = bookMapper.selectByIsbn(isbn);
    int total = usedBookMapper.countUsedBooks(isbn,minPrice,maxPrice);
    int offset=(page-1)*pageSize;
    List<UsedBook> usedBookslist = usedBookMapper.selectUsedBooks(isbn, minPrice, maxPrice, offset, pageSize);
    Map<String, Object> data = new HashMap<>();
    data.put("book", book);
    data.put("usedBooks", usedBookslist);
    data.put("total", total);
    return data;
    }
}
