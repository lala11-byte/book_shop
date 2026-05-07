// com/example/users/service/impl/FavoriteServiceImpl.java
package com.example.users.service.impl;
import com.example.users.mapper.FavoriteMapper;
import com.example.users.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteMapper favoriteMapper;

    @Override
    public void addFavorite(Integer userId, String isbn) {
        favoriteMapper.insert(userId, isbn);
    }

    @Override
    public void removeFavorite(Integer userId, String isbn) {
        favoriteMapper.delete(userId, isbn);
    }

    @Override
    public boolean isFavorited(Integer userId, String isbn) {
        return favoriteMapper.exists(userId, isbn) > 0;
    }

    @Override
    public List<Map<String, Object>> getFavorites(Integer userId) {
        return favoriteMapper.selectByUserId(userId);
    }
}