// CartItem.java
package com.example.users.pojo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CartItem {
    private Integer id;
    private Integer cartId;
    private Integer bookId;      // 对应 used_books 表的 id（即二手书ID）
    private Integer quantity;
    private LocalDateTime addedAt;
}