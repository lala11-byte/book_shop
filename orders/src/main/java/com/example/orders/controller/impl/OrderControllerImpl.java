package com.example.orders.controller.impl;

import com.example.orders.controller.OrderController;
import com.example.orders.pojo.dto.OrderPushRequest;
import com.example.orders.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderControllerImpl implements OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/pushorder")
    public ResponseEntity<Map<String, Object>> pushOrder(@RequestBody OrderPushRequest request) {
        try {
            String orderNo = orderService.createOrder(request);
            Map<String, Object> response = new HashMap<>();
            response.put("code", 200);
            response.put("message", "订单创建成功");
            response.put("orderNo", orderNo);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("code", 400);
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}