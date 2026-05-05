package com.example.users.mapper;

import com.example.users.pojo.CartItem;
import com.example.users.pojo.CartItemDetail;
import org.apache.ibatis.annotations.*;
import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface CartItemMapper {

    // 根据购物车ID和书籍ID查询已有明细
    @Select("SELECT id, cart_id, book_id, quantity, added_at FROM cart_items " +
            "WHERE cart_id = #{cartId} AND book_id = #{bookId}")
    CartItem selectByCartAndBook(@Param("cartId") Integer cartId,
                                 @Param("bookId") Integer bookId);

    // 插入新明细
    @Insert("INSERT INTO cart_items (cart_id, book_id, quantity) " +
            "VALUES (#{cartId}, #{bookId}, #{quantity})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(CartItem item);

    // 更新数量（累加/覆盖，这里用于累加，保留原方法）
    @Update("UPDATE cart_items SET quantity = #{quantity}, added_at = NOW() WHERE id = #{id}")
    int updateQuantity(@Param("id") Integer id, @Param("quantity") Integer quantity);

    // 统计购物车中商品种类数
    @Select("SELECT COUNT(*) FROM cart_items WHERE cart_id = #{cartId}")
    int countItems(@Param("cartId") Integer cartId);

    // 计算购物车总价
    @Select("SELECT COALESCE(SUM(ub.price * ci.quantity), 0.00) " +
            "FROM cart_items ci " +
            "JOIN used_books ub ON ci.book_id = ub.used_id " +
            "WHERE ci.cart_id = #{cartId}")
    BigDecimal calcTotalPrice(@Param("cartId") Integer cartId);

    // 根据明细ID查询单条记录
    @Select("SELECT id, cart_id, book_id, quantity, added_at FROM cart_items WHERE id = #{itemId}")
    CartItem selectById(@Param("itemId") Integer itemId);

    // 根据购物车ID查询所有明细（含商品信息）
// 位置：com/example/users/mapper/CartItemMapper.java
    @Select("SELECT ci.id, ci.book_id, ci.quantity, ci.added_at, " +
            "ub.price, ub.condition, b.title, ub.stock " +
            "FROM cart_items ci " +
            "JOIN used_books ub ON ci.book_id = ub.used_id " +
            "JOIN books b ON ub.isbn = b.isbn " +
            "WHERE ci.cart_id = #{cartId}")
    List<CartItemDetail> selectDetailsByCartId(@Param("cartId") Integer cartId);

    // 按ID更新数量（用于修改数量接口）
    @Update("UPDATE cart_items SET quantity = #{quantity}, added_at = NOW() WHERE id = #{id}")
    int updateQuantityById(@Param("id") Integer id, @Param("quantity") Integer quantity);

    // 按ID删除
    @Delete("DELETE FROM cart_items WHERE id = #{itemId}")
    int deleteById(@Param("itemId") Integer itemId);
}