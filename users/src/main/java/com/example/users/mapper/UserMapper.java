package com.example.users.mapper;

import com.example.users.pojo.LogInfo;
import com.example.users.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    // 用户名登录


    //邮箱登录
    User loginByEmail( String email);

    //电话登录
    User loginByPhone( String phone);
    // 新增用户
    Integer insertUser(User user);


}