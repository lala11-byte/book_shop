package com.example.users.service;

import com.example.users.pojo.OrderDetail;
import com.example.users.pojo.Orders;
import java.util.List;
import java.util.Map;

public interface OrderQueryService {
    Map<String, Object> getUserOrders(Integer userId, int page, int size);
    OrderDetail getOrderDetail(Long orderId, Integer userId);
}