package com.example.server.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.server.common.result.enums.ResultEnum;
import com.example.server.model.Admin;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;

@Component
public class JwtUtils {
    private static final String tokenPassword = "dkjashdkjahdkjfbhdskjbhdskj";

    public static String getToken(Admin admin) {
        Instant dateTime = LocalDateTime.now().plusHours(5).toInstant(OffsetDateTime.now().getOffset());
        Date expireTime = Date.from(dateTime);
        String token;
        token = JWT.create()
                .withClaim("uid", admin.getId())
                .withClaim("username", admin.getUsername())
                .withIssuedAt(new Date())
                .withExpiresAt(expireTime)
                .sign(Algorithm.HMAC256(tokenPassword));
        return token;
    }

    public static int verifyToken(String token) {
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
}
