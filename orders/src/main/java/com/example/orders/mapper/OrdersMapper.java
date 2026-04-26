package com.example.orders.mapper;// OrdersMapper.java
import com.example.orders.pojo.Orders;
import org.apache.ibatis.annotations.*;

@Mapper
public interface OrdersMapper {
    @Insert("INSERT INTO orders (order_no, user_id, recipient, phone, address_detail, " +
            "delivery_method, payment_method, remark, total_amount, status, created_at, updated_at) " +
            "VALUES (#{orderNo}, #{userId}, #{recipient}, #{phone}, #{addressDetail}, " +
            "#{deliveryMethod}, #{paymentMethod}, #{remark}, #{totalAmount}, #{status}, NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Orders order);
}