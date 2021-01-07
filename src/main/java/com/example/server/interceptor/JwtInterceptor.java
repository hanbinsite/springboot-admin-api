package com.example.server.interceptor;

import com.example.server.common.result.enums.ResultEnum;
import com.example.server.exception.ApiException;
import com.example.server.exception.NotLoginException;
import com.example.server.utils.JwtUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    private final JwtUtils jwtUtils;

    public JwtInterceptor(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        System.out.print(request.getRequestURI());
        String token = request.getHeader("Authorization");
        if (token == null) {
            throw new NotLoginException();
        }
        int result = jwtUtils.verifyToken(token);
        if (result == ResultEnum.NOT_LOGIN.getCode()) {
            throw new NotLoginException();
        } else if (result == ResultEnum.TOKEN_EXPIRE.getCode()) {
            request.setAttribute("tokenExpired", true);
        }
        Boolean has = jwtUtils.verifyRule(request.getRequestURI(), token);
        if (!has) {
            throw new ApiException(ResultEnum.NO_ACCESS_RIGHT.getCode(), "无访问权限");
        }
        request.setAttribute("tokenExpired", false);
        return true;
    }
}
