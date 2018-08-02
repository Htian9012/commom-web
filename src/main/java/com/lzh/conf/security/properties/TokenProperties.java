package com.lzh.conf.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Token认证接口属性
 * Created by htian
 */
@Configuration
@ConfigurationProperties(prefix = "app.security.token")
public class TokenProperties {
    /**
     * 登录入口
     */
    private String loginEntryPoint;
    /**
     * 刷新token入口
     */
    private String refreshEntryPoint;
    /**
     * 需要认证的接口
     */
    private String authEntryPoint;

    public String getLoginEntryPoint() {
        return loginEntryPoint;
    }

    public void setLoginEntryPoint(String loginEntryPoint) {
        this.loginEntryPoint = loginEntryPoint;
    }

    public String getRefreshEntryPoint() {
        return refreshEntryPoint;
    }

    public void setRefreshEntryPoint(String refreshEntryPoint) {
        this.refreshEntryPoint = refreshEntryPoint;
    }

    public String getAuthEntryPoint() {
        return authEntryPoint;
    }

    public void setAuthEntryPoint(String authEntryPoint) {
        this.authEntryPoint = authEntryPoint;
    }
}
