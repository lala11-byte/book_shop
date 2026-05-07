package com.example.users.mapper;

import com.example.users.pojo.Orders;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface OrdersMapper {
    @Select("SELECT * FROM orders WHERE user_id = #{userId} ORDER BY created_at DESC LIMIT #{offset}, #{size}")
    List<Orders> selectByUserId(@Param("userId") Integer userId,
                                @Param("offset") int offset,
                                @Param("size") int size);

    @Select("SELECT COUNT(*) FROM orders WHERE user_id = #{userId}")
    long countByUserId(@Param("userId") Integer userId);

    @Select("SELECT * FROM orders WHERE id = #{orderId}")
    Orders selectById(@Param("orderId") Long orderId);
}