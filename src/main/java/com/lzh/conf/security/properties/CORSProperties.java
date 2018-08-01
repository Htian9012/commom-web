package com.lzh.conf.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * CORS属性
 * Created by Acris on 2017/5/12.
 */
@Configuration
@ConfigurationProperties(prefix = "app.security.cors")
public class CORSProperties {
    /**
     * CORS映射路径
     */
    private String mapping;
    /**
     * 最大时长
     */
    private Long maxAge;
    /**
     * 是否允许认证信息
     */
    private Boolean allowCredentials;
    /**
     * 允许的源
     */
    private String allowedOrigins;
    /**
     * 允许的方法
     */
    private String allowedMethods;
    /**
     * 允许的头信息
     */
    private String allowedHeaders;

    public String getMapping() {
        return mapping;
    }

    public void setMapping(String mapping) {
        this.mapping = mapping;
    }

    public Long getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Long maxAge) {
        this.maxAge = maxAge;
    }

    public Boolean getAllowCredentials() {
        return allowCredentials;
    }

    public void setAllowCredentials(Boolean allowCredentials) {
        this.allowCredentials = allowCredentials;
    }

    public String getAllowedOrigins() {
        return allowedOrigins;
    }

    public void setAllowedOrigins(String allowedOrigins) {
        this.allowedOrigins = allowedOrigins;
    }

    public String getAllowedMethods() {
        return allowedMethods;
    }

    public void setAllowedMethods(String allowedMethods) {
        this.allowedMethods = allowedMethods;
    }

    public String getAllowedHeaders() {
        return allowedHeaders;
    }

    public void setAllowedHeaders(String allowedHeaders) {
        this.allowedHeaders = allowedHeaders;
    }
}
