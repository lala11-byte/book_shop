package com.example.users.service.impl;

import com.example.common.JwtUtil;
import com.example.users.mapper.UserMapper;
import com.example.users.pojo.LogInfo;
import com.example.users.pojo.User;
import com.example.users.service.UserService;
import com.example.users.utils.PasswordUtil;
import jakarta.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;



    @Resource
    private PasswordUtil passwordUtil;
    public LogInfo login(String key,String password,Integer type){
        User user = null;

        if (type == 1) {
            user = userMapper.loginByPhone(key);
        } else if (type == 2) {
            user = userMapper.loginByEmail(key);
        }

        if (user == null) {
            return null;
        }

        // ✅ 核心：用 BCrypt 校验
        boolean match = passwordUtil.checkPassword(password, user.getPassword());

        if (!match) {
            return null;
        }

        LogInfo logInfo = new LogInfo();
        logInfo.setId(user.getId());
        logInfo.setUsername(user.getUsername());
        logInfo.setToken(JwtUtil.generateToken(user.getId(), user.getUsername()));

        return logInfo;
    }




    @Override
    public Integer register(User user) {


        // 2️⃣ 校验验证码（这里先写死，后面再升级 Redis）
        if ("123456".equals(user.getCode())) {
            throw new RuntimeException("验证码错误");
        }

        // 3️⃣ 加密密码
        String encodedPwd = passwordUtil.encodePassword(user.getPassword());

        // 4️⃣ 封装用户
        user.setStatus("NORMAL");
        log.info(encodedPwd);
        user.setPassword(encodedPwd);

        // 5️⃣ 入库
        return userMapper.insertUser(user);

    }
}
