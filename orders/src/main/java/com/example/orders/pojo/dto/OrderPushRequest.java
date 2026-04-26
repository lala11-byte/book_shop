package com.example.orders.pojo.dto;// 请求体 DTO
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Data
public class OrderPushRequest {
    private String username;
    private String phone;
    private String addressDetail;
    private String deliveryMethod;
    private String paymentMethod;
    private List<BookItem> books;
    private String note;  // 对应 remark
    private String token;
    @Data
    public static class BookItem {
        private String title;
        private BigDecimal price;
        private Integer quantity;
        private Integer usedId;
    }
}