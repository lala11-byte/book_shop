package com.example.books.mapper;

import com.example.books.pojo.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookMapper {

    List<Book> selectBooks(
            @Param("keyword") String keyword,
            @Param("type") String type,
            @Param("author") String author,
            @Param("publisher") String publisher,
            @Param("title") String title,
            @Param("isbn") String isbn,
            @Param("offset") Integer offset,
            @Param("size") Integer size
    );

    Book selectByIsbn(@Param("isbn") String isbn);

    int countBooks(
            @Param("keyword") String keyword,
            @Param("type") String type,
            @Param("author") String author,
            @Param("publisher") String publisher,
            @Param("title") String title,
            @Param("isbn") String isbn
    );
}