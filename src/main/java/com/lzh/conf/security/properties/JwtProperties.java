package com.lzh.conf.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * JWT属性配置
 *
 * @author Acris Liu
 */
@Configuration
@ConfigurationProperties(prefix = "app.security.jwt")
public class JwtProperties {
    /**
     * Token过期时间 单位为分钟
     */
    private Integer tokenExpirationTime;

    /**
     * Token签发者
     */
    private String tokenIssuer;

    /**
     * 签名Key
     */
    private String tokenSigningKey;

    /**
     * Refresh Token过期时间 单位为分钟
     */
    private Integer refreshTokenExpTime;

    public Integer getRefreshTokenExpTime() {
        return refreshTokenExpTime;
    }

    public void setRefreshTokenExpTime(Integer refreshTokenExpTime) {
        this.refreshTokenExpTime = refreshTokenExpTime;
    }

    public Integer getTokenExpirationTime() {
        return tokenExpirationTime;
    }

    public void setTokenExpirationTime(Integer tokenExpirationTime) {
        this.tokenExpirationTime = tokenExpirationTime;
    }

    public String getTokenIssuer() {
        return tokenIssuer;
    }

    public void setTokenIssuer(String tokenIssuer) {
        this.tokenIssuer = tokenIssuer;
    }

    public String getTokenSigningKey() {
        return tokenSigningKey;
    }

    public void setTokenSigningKey(String tokenSigningKey) {
        this.tokenSigningKey = tokenSigningKey;
    }
}
