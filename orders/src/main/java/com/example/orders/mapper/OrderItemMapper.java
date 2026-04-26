package com.example.orders.mapper;// OrderItemMapper.java
import com.example.orders.pojo.OrderItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderItemMapper {
    @Insert("INSERT INTO order_items (order_id, used_id, price, quantity) " +
            "VALUES (#{orderId}, #{bookId}, #{price}, #{quantity})")
    int insert(OrderItem item);
}