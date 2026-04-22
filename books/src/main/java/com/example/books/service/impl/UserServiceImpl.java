package com.example.books.service.impl;

import com.example.books.mapper.UsedBookMapper;
import com.example.books.service.UserService;

import java.math.BigDecimal;
import java.util.Map;


public class UserServiceImpl implements UserService {
    public UsedBookMapper usedBookMapper;
    Map<String, Object> getBookDetail(
            String isbn,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            int page,
            int pageSize
    ) {
        return null;
    }
}
