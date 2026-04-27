package com.example.orders.controller;

import com.example.common.JwtUtil;
import com.example.common.Result;
import com.example.orders.pojo.Address;
import com.example.orders.service.AddressService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/getaddress")
    public Result<List<Address>> getAddressList(@RequestHeader("Authorization") String authHeader) {
        // 解析 token 获取 userId（具体实现取决于 JwtUtil 细节）
        String token = authHeader.replace("Bearer ", "");
        Integer userId = JwtUtil.parseToken(token).get("userId", Integer.class);
        if (userId == null) {
            return Result.error("用户未登录");
        }
        List<Address> list = addressService.getAddressList(userId);
        return Result.success(list);
    }
}