package com.example.users.pojo;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class OrderItem {
    private Long id;
    private Long orderId;
    private Integer bookId;
    private BigDecimal price;
    private Integer quantity;
}