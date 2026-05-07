// com/example/users/mapper/FavoriteMapper.java
package com.example.users.mapper;
import com.example.users.pojo.Favorite;
import org.apache.ibatis.annotations.*;
import java.util.List;
import java.util.Map;

@Mapper
public interface FavoriteMapper {

    // 添加收藏（使用唯一约束，重复添加会自动忽略，可根据需求改为先查后插）
    @Insert("INSERT IGNORE INTO favorites (user_id, isbn) VALUES (#{userId}, #{isbn})")
    int insert(@Param("userId") Integer userId, @Param("isbn") String isbn);

    // 取消收藏
    @Delete("DELETE FROM favorites WHERE user_id = #{userId} AND isbn = #{isbn}")
    int delete(@Param("userId") Integer userId, @Param("isbn") String isbn);

    // 检查是否已收藏
    @Select("SELECT COUNT(*) FROM favorites WHERE user_id = #{userId} AND isbn = #{isbn}")
    int exists(@Param("userId") Integer userId, @Param("isbn") String isbn);

    // 获取用户收藏列表（关联书籍信息）
    @Select("SELECT b.isbn, b.title, b.author, b.publisher, b.image, f.created_at " +
            "FROM favorites f JOIN books b ON f.isbn = b.isbn " +
            "WHERE f.user_id = #{userId} ORDER BY f.created_at DESC")
    List<Map<String, Object>> selectByUserId(Integer userId);
}