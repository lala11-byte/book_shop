package com.example.orders.mapper;

import com.example.orders.pojo.Address;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AddressMapper {

    @Select("SELECT id, user_id, recipient, phone, address, is_default " +
            "FROM addresses WHERE user_id = #{userId} ORDER BY is_default DESC, created_at DESC")
    List<Address> findByUserId(Integer userId);
}