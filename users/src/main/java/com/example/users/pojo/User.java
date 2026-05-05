package com.example.users.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String status;
    private LocalDateTime createdAt;    // 对应 created_at
    private LocalDateTime updatedAt;    // 对应 updated_at
    private String Code;                // 保留，无用可删除
}