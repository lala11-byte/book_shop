package com.example.users.pojo;

import lombok.Data;
import java.util.List;

@Data
public class OrderDetail {
    private Orders order;
    private List<OrderItemDetail> items;
}