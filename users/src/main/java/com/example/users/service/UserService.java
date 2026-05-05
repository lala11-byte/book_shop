package com.example.users.service;

import com.example.users.pojo.User;

public interface UserService {

    Integer register(User user);


    User getById(Integer userId);
    void updateBasicInfo(Integer userId, String phone, String email);
    boolean changePassword(Integer userId, String oldPwd, String newPwd);
}
