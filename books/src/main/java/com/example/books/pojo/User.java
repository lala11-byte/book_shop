package com.example.books.pojo;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "password")
public class User {
    public enum Status {
        DISABLED(0), ACTIVE(1), INACTIVE(2);
        private final int value;
        Status(int value) { this.value = value; }
        public int getValue() { return value; }
    }

    private Long id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private Status status = Status.ACTIVE; // 默认激活状态
    private LocalDateTime lastLoginAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
