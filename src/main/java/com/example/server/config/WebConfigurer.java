package com.example.server.config;

import com.example.server.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Order(0)
public class WebConfigurer implements WebMvcConfigurer {

    JwtInterceptor jwtInterceptor;

    public WebConfigurer(JwtInterceptor jwtInterceptor) {
        this.jwtInterceptor = jwtInterceptor;
    }

    // 解决浏览器端跨站安全机制的问题
    @Override
    @Order(-1)
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
//                .allowedOrigins("*")
                .allowedMethods("*")
                .maxAge(3600)
                .allowedHeaders("*");
    }

    @Override
    @Order(0)
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/**").excludePathPatterns("/admin/login");
    }
}
