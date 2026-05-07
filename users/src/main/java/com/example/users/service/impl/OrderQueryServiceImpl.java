package com.example.users.service.impl;

import com.example.users.mapper.OrderItemMapper;
import com.example.users.mapper.OrdersMapper;
import com.example.users.pojo.OrderDetail;
import com.example.users.pojo.OrderItemDetail;
import com.example.users.pojo.Orders;
import com.example.users.service.OrderQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderQueryServiceImpl implements OrderQueryService {

    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public Map<String, Object> getUserOrders(Integer userId, int page, int size) {
        long total = ordersMapper.countByUserId(userId);
        int offset = (page - 1) * size;
        List<Orders> list = ordersMapper.selectByUserId(userId, offset, size);
        Map<String, Object> data = new HashMap<>();
        data.put("total", total);
        data.put("records", list);
        return data;
    }

    @Override
    public OrderDetail getOrderDetail(Long orderId, Integer userId) {
        Orders order = ordersMapper.selectById(orderId);
        if (order == null || !order.getUserId().equals(userId)) {
            throw new RuntimeException("订单不存在或无权限");
        }
        List<OrderItemDetail> items = orderItemMapper.selectDetailByOrderId(orderId);
        OrderDetail detail = new OrderDetail();
        detail.setOrder(order);
        detail.setItems(items);
        return detail;
    }
}