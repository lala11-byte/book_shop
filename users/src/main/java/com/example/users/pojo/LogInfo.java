package com.example.users.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogInfo {
    private Integer id; //员工ID
    private String username; //用户名
    private String token; //令牌
}
