package com.example.orders.pojo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Address {
    private Integer id;
    private Integer userId;
    private String recipient;
    private String phone;
    private String address;
    private Boolean isDefault;   // 数据库 tinyint(1) 映射为 Boolean
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}