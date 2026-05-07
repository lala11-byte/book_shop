package com.example.users.service.impl;

import com.example.users.mapper.FootprintMapper;
import com.example.users.service.FootprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FootprintServiceImpl implements FootprintService {

    @Autowired
    private FootprintMapper footprintMapper;

    @Override
    public void recordFootprint(Integer userId, String isbn) {
        footprintMapper.record(userId, isbn);
    }

    @Override
    public Map<String, Object> getFootprints(Integer userId, int page, int size) {
        long total = footprintMapper.countByUserId(userId);
        int offset = (page - 1) * size;
        List<Map<String, Object>> list = footprintMapper.selectByUserId(userId, offset, size);
        Map<String, Object> data = new HashMap<>();
        data.put("total", total);
        data.put("records", list);
        return data;
    }
}