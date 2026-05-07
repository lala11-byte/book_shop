package com.example.users.pojo;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class OrderItemDetail {
    private Integer id;
    private Integer bookId;
    private BigDecimal price;
    private Integer quantity;
    private String title;     // 书名
}