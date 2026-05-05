// com/example/users/service/CartService.java
package com.example.users.service;

import com.example.users.pojo.CartItemDetail;
import java.util.List;

public interface CartService {
    void addToCart(Integer userId, Integer usedBookId, Integer quantity);
    List<CartItemDetail> getCartDetails(Integer userId);
    void updateCartItemQuantity(Integer userId, Integer itemId, Integer newQuantity);
    void deleteCartItem(Integer userId, Integer itemId);
}