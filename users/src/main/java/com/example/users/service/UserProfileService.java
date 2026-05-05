package com.example.users.service;

import com.example.users.pojo.UserProfile;

import java.util.Map;

public interface UserProfileService {
    UserProfile getByUserId(Long userId);
    void updateProfile(Long userId, Map<String, Object> updateData);
}