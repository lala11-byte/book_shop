package com.example.orders.service;

import com.example.orders.pojo.dto.OrderPushRequest;

public interface OrderService {
    String createOrder(OrderPushRequest request);
}
