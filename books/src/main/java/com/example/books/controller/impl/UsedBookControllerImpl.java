package com.example.books.controller.impl;

import com.example.books.controller.UsedBookController;
import com.example.common.Result;
import com.example.books.service.UsedBookService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/usedbooks")
public class UsedBookControllerImpl implements UsedBookController {
    @Resource
    public UsedBookService usedBookService;
    @GetMapping("/book-detail/{isbn}")
    Result<Map<String, Object>> getUsedBooks(@PathVariable String isbn, BigDecimal minPrice, BigDecimal maxPrice, int page, int pageSize)
    {
        log.info(usedBookService.getBookDetail(isbn, minPrice, maxPrice, page, pageSize).toString());
        return Result.success(usedBookService.getBookDetail(isbn, minPrice, maxPrice, page, pageSize));
    }



}