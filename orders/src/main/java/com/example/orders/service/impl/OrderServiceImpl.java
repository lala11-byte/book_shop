package com.example.orders.service.impl;

import com.example.common.JwtUtil;
import com.example.orders.pojo.OrderItem;
import com.example.orders.pojo.Orders;
import com.example.orders.pojo.dto.OrderPushRequest;
import com.example.orders.service.OrderService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;
import com.example.orders.mapper.OrdersMapper;
import com.example.orders.mapper.OrderItemMapper;
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;




    @Transactional(rollbackFor = Exception.class)
    public String createOrder(OrderPushRequest request) {
        // 1. 根据手机号或用户名获取 userId
        Claims info = JwtUtil.parseToken(request.getToken());
        Integer userId = info.get("userId", Integer.class);
        if (userId == null) {
            throw new RuntimeException("用户不存在，请先注册");
        }

        // 2. 生成订单号（示例：ORD + yyyyMMddHHmmss + 4位随机数）
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        int random = ThreadLocalRandom.current().nextInt(1000, 10000);
        String orderNo = "ORD" + timestamp + random;

        // 3. 计算总金额
        BigDecimal totalAmount = request.getBooks().stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 4. 构建订单主记录
        Orders order = new Orders();
        order.setOrderNo(orderNo);
        order.setUserId(userId);
        order.setRecipient(request.getUsername());      // 收货人用 username
        order.setPhone(request.getPhone());
        order.setAddressDetail(request.getAddressDetail());
        order.setDeliveryMethod(request.getDeliveryMethod());
        order.setPaymentMethod(request.getPaymentMethod());
        order.setRemark(request.getNote());
        order.setTotalAmount(totalAmount);
        order.setStatus("pending_payment");   // 默认待支付

        // 插入订单主表，获取自增 id
        ordersMapper.insert(order);
        Long orderId = order.getId();

        // 5. 插入订单明细
        for (OrderPushRequest.BookItem bookItem : request.getBooks()) {
            // 根据书名查找 bookId
            Integer usedId = bookItem.getUsedId();
            if (usedId == null) {
                throw new RuntimeException("书籍不存在：" + bookItem.getTitle());
            }

            OrderItem item = new OrderItem();
            item.setOrderId(orderId);
            item.setBookId(usedId);
            item.setPrice(bookItem.getPrice());
            item.setQuantity(bookItem.getQuantity());
            orderItemMapper.insert(item);
        }

        // 返回订单号给前端
        return orderNo;
    }
}