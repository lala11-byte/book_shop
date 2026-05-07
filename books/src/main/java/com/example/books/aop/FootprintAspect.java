package com.example.books.aop;

import com.example.books.mapper.FootprintMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;

@Aspect
@Component
public class FootprintAspect {

    @Autowired
    private FootprintMapper footprintMapper;

    // 拦截 BookController 的 getProfile 方法
    @AfterReturning("execution(* com.example.books.controller.*.getProfile(..))")
    public void recordFootprint(JoinPoint joinPoint) {
        try {
            // 获取当前请求
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attr == null) return;
            HttpServletRequest request = attr.getRequest();

            // 解析用户ID（仅登录用户记录）
            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) return;

            String token = authHeader.substring(7);
            Integer userId = parseUserId(token);
            if (userId == null) return;

            // 获取方法参数 isbn
            Object[] args = joinPoint.getArgs();
            String isbn = null;
            for (Object arg : args) {
                if (arg instanceof String) {
                    isbn = (String) arg;
                    break;
                }
            }
            if (isbn == null) return;

            // 写入足迹表
            footprintMapper.record(userId, isbn);
        } catch (Exception e) {
            // 记录失败不影响主流程
        }
    }

    private Integer parseUserId(String token) {
        try {
            // 请根据实际 JWT 密钥和解析方式调整
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey("books-shop-secret-books-shop-secret".getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.get("userId", Integer.class);
        } catch (Exception e) {
            return null;
        }
    }
}