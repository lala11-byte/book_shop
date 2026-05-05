package com.example.users.controller;

import com.example.common.Result;
import com.example.users.service.OssService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/info")
public class OssTokenController {

    @Resource
    private OssService ossService;

    @GetMapping("/oss/token")
    public Result getOssToken() {
        try {
            Map<String, String> token = ossService.generateStsToken();
            return Result.success(token);
        } catch (Exception e) {
            e.printStackTrace();   // 后端控制台输出全部堆栈
            return Result.error("OSS凭证失败：" + e.getMessage());
        }
    }
}