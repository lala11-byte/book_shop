package com.example.users.controller;

import com.example.common.JwtUtil;
import com.example.common.Result;
import com.example.users.pojo.CartItemDetail;
import com.example.users.service.CartService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    /** 添加商品到购物车 */
    @PostMapping("/add")
    public Result addToCart(@RequestHeader("Authorization") String authHeader,
                            @RequestBody Map<String, Object> body) {
        Integer userId = getUserIdFromHeader(authHeader);
        if (userId == null) return Result.error("未登录");

        Integer bookId = (Integer) body.get("bookId");
        Integer quantity = (Integer) body.get("quantity");
        if (bookId == null || quantity == null || quantity < 1) {
            return Result.error("参数错误");
        }

        try {
            cartService.addToCart(userId, bookId, quantity);
            return Result.success("已加入购物车");
        } catch (Exception e) {
            return Result.error("加入购物车失败：" + e.getMessage());
        }
    }

    /** 获取购物车内容 */
    @GetMapping
    public Result getCart(@RequestHeader("Authorization") String authHeader) {
        Integer userId = getUserIdFromHeader(authHeader);
        if (userId == null) return Result.error("未登录");

        List<CartItemDetail> items = cartService.getCartDetails(userId);
        return Result.success(Map.of("items", items));
    }

    /** 更新商品数量 */
    @PutMapping("/items/{itemId}")
    public Result updateQuantity(@RequestHeader("Authorization") String authHeader,
                                 @PathVariable Integer itemId,
                                 @RequestBody Map<String, Integer> body) {
        Integer userId = getUserIdFromHeader(authHeader);
        if (userId == null) return Result.error("未登录");

        Integer quantity = body.get("quantity");
        if (quantity == null || quantity < 1) {
            return Result.error("数量不合法");
        }

        try {
            cartService.updateCartItemQuantity(userId, itemId, quantity);
            return Result.success(null);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    /** 删除商品 */
    @DeleteMapping("/items/{itemId}")
    public Result deleteItem(@RequestHeader("Authorization") String authHeader,
                             @PathVariable Integer itemId) {
        Integer userId = getUserIdFromHeader(authHeader);
        if (userId == null) return Result.error("未登录");

        try {
            cartService.deleteCartItem(userId, itemId);
            return Result.success(null);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    /** 从请求头中解析 userId */
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