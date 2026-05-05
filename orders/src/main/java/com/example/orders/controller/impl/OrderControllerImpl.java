package com.example.orders.controller.impl;

import com.example.common.Result;
import com.example.orders.controller.OrderController;
import com.example.orders.pojo.dto.OrderPushRequest;
import com.example.orders.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/order")
public class OrderControllerImpl implements OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/pushorder")
    public Result pushOrder(@RequestBody OrderPushRequest request) {

        try {
           String orderNo = orderService.createOrder(request);
            System.out.println("生成的订单号: " + orderNo);
           return Result.success(orderNo);

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("创建失败");
        }
    }
}