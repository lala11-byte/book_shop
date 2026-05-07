// com/example/users/service/FavoriteService.java
package com.example.users.service;
import java.util.List;
import java.util.Map;

public interface FavoriteService {
    void addFavorite(Integer userId, String isbn);
    void removeFavorite(Integer userId, String isbn);
    boolean isFavorited(Integer userId, String isbn);
    List<Map<String, Object>> getFavorites(Integer userId);
}