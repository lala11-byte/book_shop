package com.example.users.controller;

import com.example.common.JwtUtil;
import com.example.common.Result;
import com.example.users.service.FootprintService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/footprints")
public class FootprintController {

    @Autowired
    private FootprintService footprintService;

    // 获取足迹列表（分页）
    @GetMapping
    public Result getFootprints(@RequestHeader("Authorization") String authHeader,
                                @RequestParam(defaultValue = "1") int page,
                                @RequestParam(defaultValue = "12") int size) {
        Integer userId = getUserIdFromHeader(authHeader);
        if (userId == null) return Result.error("未登录");

        Map<String, Object> data = footprintService.getFootprints(userId, page, size);
        return Result.success(data);
    }

    // 记录足迹（AOP/拦截器也可以调用此接口，但通常由 AOP 或前端直接调用）
    @PostMapping("/record")
    public Result recordFootprint(@RequestHeader("Authorization") String authHeader,
                                  @RequestBody Map<String, String> body) {
        Integer userId = getUserIdFromHeader(authHeader);
        if (userId == null) return Result.error("未登录");

        String isbn = body.get("isbn");
        if (isbn == null || isbn.isEmpty()) return Result.error("参数缺失");

        footprintService.recordFootprint(userId, isbn);
        return Result.success(null);
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