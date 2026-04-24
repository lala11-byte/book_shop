package com.example.users.pojo;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserProfile {

    private Long id;

    private Long userId;

    private String nickname;

    private String avatar;

    private String gender; // 不用 enum，方便扩展

    private LocalDate birthDate;

    private String realName;

    private String idCard;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}