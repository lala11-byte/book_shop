package com.example.books.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {
    private enum Gender{
    male,female,unkonwn;
    }
    private Long id;
    private Integer userId;
    private String nickname = "新用户";
    private String avatar = "/default_avatar.png";
    private Gender gender = Gender.unkonwn;
    private LocalDate birthDate;
    private String realName;
    private String idCard;
}
