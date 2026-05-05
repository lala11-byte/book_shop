// com/example/users/service/impl/CartServiceImpl.java
package com.example.users.service.impl;

import com.example.users.mapper.*;
import com.example.users.pojo.*;
import com.example.users.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private CartItemMapper cartItemMapper;
    @Autowired
    private UsedBookMapper usedBookMapper;

    @Override
    @Transactional
    public void addToCart(Integer userId, Integer usedBookId, Integer quantity) {
        // 校验库存
        Integer stock = usedBookMapper.selectStockById(usedBookId);
        if (stock == null || stock <= 0) {
            throw new RuntimeException("该二手书已售罄或不存在");
        }

        // 获取或创建购物车
        Cart cart = cartMapper.selectByUserId(userId);
        if (cart == null) {
            cart = new Cart();
            cart.setUserId(userId);
            cartMapper.insert(cart);
        }

        // 检查已有数量
        CartItem existingItem = cartItemMapper.selectByCartAndBook(cart.getId(), usedBookId);
        int currentQty = existingItem != null ? existingItem.getQuantity() : 0;
        int targetQty = currentQty + quantity;

        if (targetQty > stock) {
            throw new RuntimeException("库存不足，最多还能添加 " + (stock - currentQty) + " 本");
        }

        if (existingItem != null) {
            cartItemMapper.updateQuantity(existingItem.getId(), targetQty);
        } else {
            CartItem newItem = new CartItem();
            newItem.setCartId(cart.getId());
            newItem.setBookId(usedBookId);
            newItem.setQuantity(quantity);
            cartItemMapper.insert(newItem);
        }

        // 更新统计
        int itemCount = cartItemMapper.countItems(cart.getId());
        BigDecimal totalPrice = cartItemMapper.calcTotalPrice(cart.getId());
        cartMapper.updateTotals(cart.getId(), itemCount, totalPrice);
    }

    @Override
    public List<CartItemDetail> getCartDetails(Integer userId) {
        Cart cart = cartMapper.selectByUserId(userId);
        if (cart == null) {
            return List.of();
        }
        return cartItemMapper.selectDetailsByCartId(cart.getId());
    }

    @Override
    @Transactional
    public void updateCartItemQuantity(Integer userId, Integer itemId, Integer newQuantity) {
        // 获取明细，验证所属购物车属于该用户
        CartItem item = cartItemMapper.selectById(itemId);
        if (item == null) throw new RuntimeException("商品不存在");

        Cart cart = cartMapper.selectByUserId(userId);
        if (cart == null || !cart.getId().equals(item.getCartId())) {
            throw new RuntimeException("无权操作该购物车");
        }

        // 校验库存
        Integer stock = usedBookMapper.selectStockById(item.getBookId());
        if (stock == null || newQuantity > stock) {
            throw new RuntimeException("库存不足，最多可购 " + stock + " 本");
        }

        cartItemMapper.updateQuantityById(itemId, newQuantity);

        // 更新统计
        int itemCount = cartItemMapper.countItems(cart.getId());
        BigDecimal totalPrice = cartItemMapper.calcTotalPrice(cart.getId());
        cartMapper.updateTotals(cart.getId(), itemCount, totalPrice);
    }

    @Override
    @Transactional
    public void deleteCartItem(Integer userId, Integer itemId) {
        CartItem item = cartItemMapper.selectById(itemId);
        if (item == null) throw new RuntimeException("商品不存在");

        Cart cart = cartMapper.selectByUserId(userId);
        if (cart == null || !cart.getId().equals(item.getCartId())) {
            throw new RuntimeException("无权操作该购物车");
        }

        cartItemMapper.deleteById(itemId);

        // 更新统计
        int itemCount = cartItemMapper.countItems(cart.getId());
        BigDecimal totalPrice = cartItemMapper.calcTotalPrice(cart.getId());
        cartMapper.updateTotals(cart.getId(), itemCount, totalPrice);
    }
}