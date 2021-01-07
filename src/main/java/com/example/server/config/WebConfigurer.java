package com.example.server.config;

import com.example.server.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    JwtInterceptor jwtInterceptor;

    public WebConfigurer(JwtInterceptor jwtInterceptor) {
        this.jwtInterceptor = jwtInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        List<String> notVer = new ArrayList<>();
//        notVer.add("admin/login");
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/**").excludePathPatterns("/admin/login");
    }
    // 解决浏览器端跨站安全机制的问题
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("Authorization");
    }
}
