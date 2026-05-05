package com.example.users.mapper;

import com.example.users.pojo.LogInfo;
import com.example.users.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    // 用户名登录


    //邮箱登录
    User loginByEmail( String email);

    //电话登录
    User loginByPhone( String phone);
    // 新增用户
    Integer insertUser(User user);

    @Select("SELECT id, username, password, email, phone, status, created_at, updated_at FROM users WHERE id = #{id}")
    User selectById(@Param("id") Integer id);

    @Update("UPDATE users SET phone = COALESCE(#{phone}, phone), email = COALESCE(#{email}, email) WHERE id = #{userId}")
    void updateBasicInfo(@Param("userId") Integer userId, @Param("phone") String phone, @Param("email") String email);

    @Update("UPDATE users SET password = #{password} WHERE id = #{userId}")
    void updatePassword(@Param("userId") Integer userId, @Param("password") String password);

}