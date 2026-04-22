package com.example.books.pojo;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 二手书实体类
 */
@Data
public class UsedBook {

    private Integer usedId;


    private String isbn;


    private String condition;


    private BigDecimal price;


    private Integer stock;


    private Integer sellerId;


    private LocalDateTime createdAt;
}
