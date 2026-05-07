// com/example/users/pojo/Favorite.java
package com.example.users.pojo;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Favorite {
    private Integer id;
    private Integer userId;
    private String isbn;
    private LocalDateTime createdAt;
}