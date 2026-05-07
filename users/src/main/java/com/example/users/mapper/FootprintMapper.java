package com.example.users.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface FootprintMapper {

    // 记录或更新足迹
    @Insert("INSERT INTO user_footprints (user_id, isbn) VALUES (#{userId}, #{isbn}) " +
            "ON DUPLICATE KEY UPDATE created_at = NOW()")
    int record(@Param("userId") Integer userId, @Param("isbn") String isbn);

    // 查询足迹列表（分页）
    @Select("SELECT f.isbn, b.title, b.author, b.image, f.created_at " +
            "FROM user_footprints f " +
            "JOIN books b ON f.isbn = b.isbn " +
            "WHERE f.user_id = #{userId} " +
            "ORDER BY f.created_at DESC LIMIT #{offset}, #{size}")
    List<Map<String, Object>> selectByUserId(@Param("userId") Integer userId,
                                             @Param("offset") int offset,
                                             @Param("size") int size);

    // 总数
    @Select("SELECT COUNT(*) FROM user_footprints WHERE user_id = #{userId}")
    long countByUserId(@Param("userId") Integer userId);
}