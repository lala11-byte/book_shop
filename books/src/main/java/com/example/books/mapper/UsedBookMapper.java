package com.example.books.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.books.pojo.UsedBook;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;


@Mapper
public interface UsedBookMapper {
    List<UsedBook> selectUsedBooks(String isbn, BigDecimal minPrice, BigDecimal maxPrice, Integer offset,
                                   Integer pageSize);
    Integer countUsedBooks (String isbn,BigDecimal minPrice, BigDecimal maxPrice);

}

