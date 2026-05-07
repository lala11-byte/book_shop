package com.example.users.mapper;

import com.example.users.pojo.OrderItemDetail;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface OrderItemMapper {
    // com/example/users/mapper/OrderItemMapper.java
    @Select("SELECT oi.id, oi.used_id AS bookId, oi.price, oi.quantity, b.title " +
            "FROM order_items oi " +
            "JOIN used_books ub ON oi.used_id = ub.used_id " +
            "JOIN books b ON ub.isbn = b.isbn " +
            "WHERE oi.order_id = #{orderId}")
    List<OrderItemDetail> selectDetailByOrderId(@Param("orderId") Long orderId);
}