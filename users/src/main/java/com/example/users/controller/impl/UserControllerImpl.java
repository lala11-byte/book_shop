package com.example.users.controller.impl;

import com.example.common.Result;
import com.example.users.pojo.LogInfo;
import com.example.users.pojo.User;
import com.example.users.service.UserService;
import com.example.users.service.impl.UserServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
@Slf4j
public class UserControllerImpl {
    @Resource
    UserServiceImpl userService;
    @PostMapping("/login")
    public Result login(@RequestBody Map<String, Object> requestData)
    {
        String key = (String) requestData.get("key");
        String password = (String) requestData.get("password");
        Integer type = (Integer) requestData.get("type");
        LogInfo logInfo = userService.login(key,password,type);
        if(logInfo != null)
        {
        return Result.success(logInfo) ;
        }
        else{
            return Result.error("账号或密码错误");
        }
    }



    @PostMapping("/register")
    public Result register(@RequestBody User user) {

        Integer value = userService.register(user);

        if(value.equals(1)){
            return Result.success("成功注册");
        }
        else{
            return Result.error("注册失败");
        }
    }

}
