package com.example.orders.pojo;// OrderItem.java
import lombok.Data;
import java.math.BigDecimal;

@Data
public class OrderItem {
    private Long id;
    private Long orderId;
    private Integer bookId;
    private BigDecimal price;
    private Integer quantity;
    // subtotal 为计算列，无需设置
}