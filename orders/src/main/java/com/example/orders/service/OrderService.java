package com.example.orders.service;

import com.example.orders.pojo.Address;
import com.example.orders.pojo.dto.OrderPushRequest;

import java.util.List;

public interface OrderService {
    String createOrder(OrderPushRequest request);


}
