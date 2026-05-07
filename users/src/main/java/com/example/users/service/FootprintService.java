package com.example.users.service;

import java.util.Map;

public interface FootprintService {
    void recordFootprint(Integer userId, String isbn);
    Map<String, Object> getFootprints(Integer userId, int page, int size);
}