package com.example.books.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public interface UsedBookService {
     Map<String, Object> getBookDetail(
            String isbn,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            Integer page,
            Integer pageSize
    );
}

