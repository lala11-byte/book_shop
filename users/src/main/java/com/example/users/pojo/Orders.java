package com.example.users.pojo;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Orders {
    private Long id;
    private String orderNo;
    private Integer userId;
    private String recipient;
    private String phone;
    private String addressDetail;
    private String deliveryMethod;
    private String paymentMethod;
    private String remark;
    private BigDecimal totalAmount;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime paidAt;
    private LocalDateTime updatedAt;
}