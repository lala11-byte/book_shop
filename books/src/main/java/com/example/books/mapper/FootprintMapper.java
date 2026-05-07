package com.example.books.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FootprintMapper {
    @Insert("INSERT INTO user_footprints (user_id, isbn) VALUES (#{userId}, #{isbn}) " +
            "ON DUPLICATE KEY UPDATE created_at = NOW()")
    int record(@Param("userId") Integer userId, @Param("isbn") String isbn);
}