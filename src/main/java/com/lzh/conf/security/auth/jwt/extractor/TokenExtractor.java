package com.lzh.conf.security.auth.jwt.extractor;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;

import com.google.common.base.Strings;
import com.lzh.conf.security.config.WebSecurityConfig;

/**
 * Token提取器
 *
 * @author Acris Liu
 */
@Component
public class TokenExtractor {

    public String extract(String header) {
        if (Strings.isNullOrEmpty(header)) {
            throw new AuthenticationServiceException("认证头信息不能为空");
        }

        if (header.length() < WebSecurityConfig.JWT_TOKEN_HEADER_PREFIX.length()) {
            throw new AuthenticationServiceException("头信息大小不合法");
        }

        return header.substring(WebSecurityConfig.JWT_TOKEN_HEADER_PREFIX.length(), header.length());
    }
}
