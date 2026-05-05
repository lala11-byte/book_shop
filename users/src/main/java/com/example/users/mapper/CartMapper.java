// CartMapper.java
package com.example.users.mapper;

import com.example.users.pojo.Cart;
import org.apache.ibatis.annotations.*;
import java.math.BigDecimal;

@Mapper
public interface CartMapper {

    @Select("SELECT id, user_id, item_count, total_price, created_at, updated_at " +
            "FROM carts WHERE user_id = #{userId}")
    Cart selectByUserId(@Param("userId") Integer userId);

    @Insert("INSERT INTO carts (user_id, item_count, total_price) " +
            "VALUES (#{userId}, 0, 0.00)")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Cart cart);

    @Update("UPDATE carts SET item_count = #{itemCount}, total_price = #{totalPrice}, " +
            "updated_at = NOW() WHERE id = #{cartId}")
    int updateTotals(@Param("cartId") Integer cartId,
                     @Param("itemCount") Integer itemCount,
                     @Param("totalPrice") BigDecimal totalPrice);
}