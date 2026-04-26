package com.example.orders.pojo;// Orders.java
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
    private String deliveryMethod;  // delivery / pickup
    private String paymentMethod;   // alipay / wechat / bankcard
    private String remark;
    private BigDecimal totalAmount;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime paidAt;
    private LocalDateTime updatedAt;
}