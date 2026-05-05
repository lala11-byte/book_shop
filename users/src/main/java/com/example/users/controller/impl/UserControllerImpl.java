package com.example.users.controller.impl;

import com.example.common.JwtUtil;
import com.example.common.Result;
import com.example.users.pojo.LogInfo;
import com.example.users.pojo.User;
import com.example.users.pojo.UserProfile;
import com.example.users.service.UserProfileService;
import com.example.users.service.UserService;
import com.example.users.service.impl.UserServiceImpl;
import io.jsonwebtoken.Claims;
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


    @Resource
    private UserProfileService userProfileService;

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



    /**
     * 获取个人信息
     */
    @GetMapping("/profile")
    public Result getUserProfile(@RequestHeader("Authorization") String authHeader) {
        Integer userId = getUserIdFromHeader(authHeader);
        if (userId == null) {
            return Result.error("未登录");
        }

        User user = userService.getById(userId);
        if (user == null) {
            return Result.error("用户不存在");
        }
        user.setPassword(null);  // 不暴露密码

        UserProfile profile = userProfileService.getByUserId(userId.longValue());

        Map<String, Object> data = new HashMap<>();
        data.put("user", user);
        data.put("profile", profile);
        return Result.success(data);
    }

    /**
     * 更新个人信息
     */
    @PutMapping("/profile")
    public Result updateProfile(@RequestHeader("Authorization") String authHeader,
                                @RequestBody Map<String, Object> updateData) {
        Integer userId = getUserIdFromHeader(authHeader);
        if (userId == null) {
            return Result.error("未登录");
        }

        // 更新 user 表：phone, email
        String phone = (String) updateData.get("phone");
        String email = (String) updateData.get("email");
        if (phone != null || email != null) {
            userService.updateBasicInfo(userId, phone, email);
        }

        // 更新 user_profile 表
        userProfileService.updateProfile(userId.longValue(), updateData);

        return Result.success(null);
    }

    /**
     * 修改密码
     */
    @PutMapping("/password")
    public Result changePassword(@RequestHeader("Authorization") String authHeader,
                                 @RequestBody Map<String, String> pwdData) {
        Integer userId = getUserIdFromHeader(authHeader);
        if (userId == null) {
            return Result.error("未登录");
        }

        String oldPwd = pwdData.get("oldPassword");
        String newPwd = pwdData.get("newPassword");
        if (oldPwd == null || newPwd == null) {
            return Result.error("参数缺失");
        }

        boolean ok = userService.changePassword(userId, oldPwd, newPwd);
        return ok ? Result.success(null) : Result.error("旧密码错误");
    }

    private Integer getUserIdFromHeader(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            try {
                Claims claims = JwtUtil.parseToken(authHeader.substring(7));
                return claims.get("userId", Integer.class);
            } catch (Exception ignored) {}
        }
        return null;
    }
}
