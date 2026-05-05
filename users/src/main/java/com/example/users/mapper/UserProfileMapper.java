package com.example.users.mapper;

import com.example.users.pojo.UserProfile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserProfileMapper {

    UserProfile findByUserId(@Param("userId") Long userId);

    int insert(UserProfile userProfile);

    int update(UserProfile userProfile);

    @Select("SELECT id, user_id, nickname, avatar, gender, birth_date, real_name, id_card " +
            "FROM user_profile WHERE user_id = #{userId}")
    UserProfile selectByUserId(@Param("userId") Long userId);

    @Update("UPDATE user_profile SET " +
            "nickname = COALESCE(#{nickname}, nickname), " +
            "avatar = COALESCE(#{avatar}, avatar), " +
            "gender = COALESCE(#{gender}, gender), " +
            "birth_date = COALESCE(#{birthDate}, birth_date), " +
            "real_name = COALESCE(#{realName}, real_name), " +
            "id_card = COALESCE(#{idCard}, id_card) " +
            "WHERE user_id = #{userId}")
    int updateByUserId(UserProfile profile);
}