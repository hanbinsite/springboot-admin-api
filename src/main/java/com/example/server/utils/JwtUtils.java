package com.example.server.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.server.common.result.enums.ResultEnum;
import com.example.server.model.Admin;
import com.example.server.service.impl.AdminServiceImpl;
import com.example.server.service.impl.GroupRuleServiceImpl;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;

@Component
public class JwtUtils {
    private final String tokenPassword = "dkjashdkjahdkjfbhdskjbhdskj";

    private final RedisUtils redisUtils;
    private final GroupRuleServiceImpl groupRuleService;
    private final AdminServiceImpl adminService;

    public JwtUtils(RedisUtils redisUtils, GroupRuleServiceImpl groupRuleService, AdminServiceImpl adminService) {
        this.redisUtils = redisUtils;
        this.groupRuleService = groupRuleService;
        this.adminService = adminService;
    }

    public String getToken(Admin admin) {
        Instant dateTime = LocalDateTime.now().plusHours(5).toInstant(OffsetDateTime.now().getOffset());
        Date expireTime = Date.from(dateTime);
        String token;
        token = JWT.create()
                .withClaim("uid", admin.getId())
                .withClaim("username", admin.getUsername())
                .withIssuedAt(new Date())
                .withExpiresAt(expireTime)
                .sign(Algorithm.HMAC256(tokenPassword));
        redisUtils.set("admin:token:" + token, admin.getId().toString(), 18000);
        return token;
    }

    public int verifyToken(String token) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(tokenPassword)).build();
        try {
            jwtVerifier.verify(token);
        } catch (TokenExpiredException e) {
            return ResultEnum.TOKEN_EXPIRE.getCode();
        } catch (JWTVerificationException e) {
            return ResultEnum.NOT_LOGIN.getCode();
        }
        return ResultEnum.SUCCESS.getCode();
    }

    public Boolean verifyRule(String url, String token) {
        Object cache = redisUtils.get("admin:token:" + token);
        if (cache == null) {
            return false;
        }
        String id = cache.toString();
        Admin admin = adminService.getAdminById(Integer.parseInt(id));
        if (admin == null) {
            return false;
        }
        return groupRuleService.getRuleByApiAndGroupId(url, admin.getGroupId());
    }
}
