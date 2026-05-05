// 辅助查询结果类（可放在 pojo 目录下）
package com.example.users.pojo;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CartItemDetail {
    private Integer id;
    private Integer cartId;
    private Integer bookId;
    private Integer quantity;
    private LocalDateTime addedAt;
    private BigDecimal price;
    private String condition;
    private String title;  // 书籍名称
}