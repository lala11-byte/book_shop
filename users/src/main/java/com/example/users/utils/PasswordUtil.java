package com.example.users.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtil {

    private final BCryptPasswordEncoder passwordEncoder;

    public PasswordUtil() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // 加密密码
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    // 验证密码
    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}