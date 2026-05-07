// com/example/users/controller/FavoriteController.java
package com.example.users.controller;
import com.example.common.JwtUtil;
import com.example.common.Result;
import com.example.users.service.FavoriteService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    // 获取当前用户收藏列表
    @GetMapping
    public Result getFavorites(@RequestHeader("Authorization") String authHeader) {
        Integer userId = getUserId(authHeader);
        if (userId == null) return Result.error("未登录");
        List<Map<String, Object>> list = favoriteService.getFavorites(userId);
        return Result.success(list);
    }

    // 检查是否已收藏某书
    @GetMapping("/check")
    public Result checkFavorited(@RequestHeader("Authorization") String authHeader,
                                 @RequestParam String isbn) {
        Integer userId = getUserId(authHeader);
        if (userId == null) return Result.error("未登录");
        boolean favorited = favoriteService.isFavorited(userId, isbn);
        return Result.success(Map.of("isFavorited", favorited));
    }

    // 添加收藏
    @PostMapping("/{isbn}")
    public Result addFavorite(@RequestHeader("Authorization") String authHeader,
                              @PathVariable String isbn) {
        Integer userId = getUserId(authHeader);
        if (userId == null) return Result.error("未登录");
        try {
            favoriteService.addFavorite(userId, isbn);
            return Result.success("已收藏");
        } catch (Exception e) {
            return Result.error("收藏失败：" + e.getMessage());
        }
    }

    // 取消收藏
    @DeleteMapping("/{isbn}")
    public Result removeFavorite(@RequestHeader("Authorization") String authHeader,
                                 @PathVariable String isbn) {
        Integer userId = getUserId(authHeader);
        if (userId == null) return Result.error("未登录");
        favoriteService.removeFavorite(userId, isbn);
        return Result.success("已取消收藏");
    }

    private Integer getUserId(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            try {
                Claims claims = JwtUtil.parseToken(authHeader.substring(7));
                return claims.get("userId", Integer.class);
            } catch (Exception ignored) {}
        }
        return null;
    }
}