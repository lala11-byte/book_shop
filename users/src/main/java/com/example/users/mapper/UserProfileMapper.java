package com.example.users.mapper;

import com.example.users.pojo.UserProfile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserProfileMapper {

    UserProfile findByUserId(@Param("userId") Long userId);

    int insert(UserProfile userProfile);

    int update(UserProfile userProfile);
}