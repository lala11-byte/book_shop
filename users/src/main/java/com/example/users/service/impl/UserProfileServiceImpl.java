package com.example.users.service.impl;

import com.example.users.mapper.UserProfileMapper;
import com.example.users.pojo.UserProfile;
import com.example.users.service.UserProfileService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Resource
    private UserProfileMapper userProfileMapper;

    @Override
    public UserProfile getByUserId(Long userId) {
        return userProfileMapper.selectByUserId(userId);
    }

    @Override
    public void updateProfile(Long userId, Map<String, Object> updateData) {
        // 1. 检查是否已有 profile 记录
        UserProfile existing = userProfileMapper.selectByUserId(userId);
        if (existing == null) {
            // 2. 插入一条初始记录（只包含 userId，其他字段为数据库默认值或 null）
            UserProfile newProfile = new UserProfile();
            newProfile.setUserId(userId);
// 从 updateData 中获取昵称，若没有则用数据库默认值
            String nickname = (String) updateData.getOrDefault("nickname", "新用户");
            newProfile.setNickname(nickname);
            // 其他字段同理
            newProfile.setAvatar((String) updateData.get("avatar"));
            newProfile.setGender((String) updateData.get("gender"));
            newProfile.setRealName((String) updateData.get("realName"));
            newProfile.setIdCard((String) updateData.get("idCard"));
            if (updateData.containsKey("birthDate")) {
                String birthStr = (String) updateData.get("birthDate");
                if (birthStr != null && !birthStr.isEmpty()) {
                    newProfile.setBirthDate(LocalDate.parse(birthStr));
                }
            }
            userProfileMapper.insert(newProfile);
        }

        // 3. 构造更新对象并执行更新（此时一定会命中一行）
        UserProfile updateObj = new UserProfile();
        updateObj.setUserId(userId);
        if (updateData.containsKey("nickname")) updateObj.setNickname((String) updateData.get("nickname"));
        if (updateData.containsKey("avatar")) updateObj.setAvatar((String) updateData.get("avatar"));
        if (updateData.containsKey("gender")) updateObj.setGender((String) updateData.get("gender"));
        if (updateData.containsKey("realName")) updateObj.setRealName((String) updateData.get("realName"));
        if (updateData.containsKey("idCard")) updateObj.setIdCard((String) updateData.get("idCard"));
        if (updateData.containsKey("birthDate")) {
            String birthStr = (String) updateData.get("birthDate");
            if (birthStr != null && !birthStr.isEmpty()) {
                updateObj.setBirthDate(LocalDate.parse(birthStr));
            }
        }

        int rows = userProfileMapper.updateByUserId(updateObj);
        // rows 此时应为 1（若仍为 0 则有问题，可打日志排查）
    }
}