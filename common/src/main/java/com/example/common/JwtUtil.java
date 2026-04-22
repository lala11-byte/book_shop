package com.example.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private static final String SECRET = "books-shop-secret-books-shop-secret";
    private static final long EXPIRE = 7 * 24 * 60 * 60 * 1000;

    private static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    // ✅ 生成token（存多个字段）
    public String generateToken(Integer userId, String username) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("username", username);

        return Jwts.builder()
                .setClaims(claims)  // ✅ 放自定义数据
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(KEY)
                .compact();
    }

    // ✅ 解析token（返回Claims）
    public Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}