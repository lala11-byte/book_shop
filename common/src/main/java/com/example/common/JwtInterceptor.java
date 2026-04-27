package com.example.common;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
@Configuration
public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(401);
            return false;
        }

        String token = authHeader.substring(7);

        try {
            // 校验 token（你自己的工具类）
            Claims claims = JwtUtil.parseToken(token);
            Integer userId = claims.get("userId", Integer.class);
            request.setAttribute("currentUserId", userId);
        } catch (Exception e) {
            response.setStatus(401);
            return false;
        }

        return true;
    }
}