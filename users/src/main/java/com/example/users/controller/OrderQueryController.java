package com.example.users.controller;

import com.example.common.JwtUtil;
import com.example.common.Result;
import com.example.users.pojo.OrderDetail;
import com.example.users.service.OrderQueryService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderQueryController {

    @Autowired
    private OrderQueryService orderQueryService;

    /** 订单列表（分页） */
    @GetMapping("/list")
    public Result getUserOrders(@RequestHeader("Authorization") String authHeader,
                                @RequestParam(defaultValue = "1") int page,
                                @RequestParam(defaultValue = "10") int size) {
        Integer userId = getUserIdFromHeader(authHeader);
        if (userId == null) return Result.error("未登录");

        Map<String, Object> data = orderQueryService.getUserOrders(userId, page, size);
        return Result.success(data);
    }

    /** 订单详情 */
    @GetMapping("/{orderId}")
    public Result getOrderDetail(@RequestHeader("Authorization") String authHeader,
                                 @PathVariable Long orderId) {
        Integer userId = getUserIdFromHeader(authHeader);
        if (userId == null) return Result.error("未登录");

        try {
            OrderDetail detail = orderQueryService.getOrderDetail(orderId, userId);
            return Result.success(detail);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    private Integer getUserIdFromHeader(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            try {
                Claims claims = JwtUtil.parseToken(authHeader.substring(7));
                return claims.get("userId", Integer.class);
            } catch (Exception ignored) {}
        }
        return null;
    }
}