package com.example.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor())
                .addPathPatterns("/**")   // ✅ 拦截所有接口
                .excludePathPatterns(
                        "/user/login",
                        "/user/register",
                        "/error"
                );
    }
}