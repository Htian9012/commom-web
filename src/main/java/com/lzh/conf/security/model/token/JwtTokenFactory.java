package com.lzh.conf.security.model.token;


import static java.util.stream.Collectors.toList;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Strings;
import com.lzh.conf.security.model.Scopes;
import com.lzh.conf.security.model.UserContext;
import com.lzh.conf.security.properties.JwtProperties;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Token生产工厂类
 *
 * @author Acris Liu
 */
@Component
public class JwtTokenFactory {
    private final JwtProperties jwtProperties;

    @Autowired
    public JwtTokenFactory(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    /**
     * 创建JWT Token
     *
     * @param userContext
     * @return AccessJwtToken
     */
    public AccessJwtToken createAccessJwtToken(UserContext userContext) {
        if (Strings.isNullOrEmpty(userContext.getUsername()))
            throw new IllegalArgumentException("用户名为空，无法创建Token。");

        Claims claims = Jwts.claims().setSubject(userContext.getUsername());

        claims.put("scopes", userContext.getAuthorities().stream().map(authority -> authority.toString()).collect(toList()));

        LocalDateTime currentDateTime = LocalDateTime.now();

        //生成token
        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuer(jwtProperties.getTokenIssuer())
                .setIssuedAt(Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(currentDateTime.plusMinutes(jwtProperties.getTokenExpirationTime()).atZone(ZoneId.systemDefault()).toInstant()))
                .compressWith(CompressionCodecs.GZIP)
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getTokenSigningKey())
                .compact();

        return new AccessJwtToken(token, claims);
    }

    /**
     * 创建Refresh Token
     *
     * @param userContext
     * @return JwtToken
     */
    public JwtToken createRefreshToken(UserContext userContext) {
        if (Strings.isNullOrEmpty(userContext.getUsername())) {
            throw new IllegalArgumentException("用户名为空，无法创建Refresh Token。");
        }

        LocalDateTime currentDateTime = LocalDateTime.now();

        Claims claims = Jwts.claims().setSubject(userContext.getUsername());
        //设定Refresh Token标识
        claims.put("scopes", Arrays.asList(Scopes.REFRESH_TOKEN.authority()));

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuer(jwtProperties.getTokenIssuer())
                //设置JTI 方便吊销Refresh Token
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(currentDateTime.plusMinutes(jwtProperties.getRefreshTokenExpTime()).atZone(ZoneId.systemDefault()).toInstant()))
                .compressWith(CompressionCodecs.GZIP)
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getTokenSigningKey())
                .compact();

        return new AccessJwtToken(token, claims);
    }
}
