package com.lzh.conf.security.exceptions;

import org.springframework.security.core.AuthenticationException;

import com.lzh.conf.security.model.token.JwtToken;

/**
 * Token过期错误类
 *
 * @author Acris Liu
 */
public class JwtExpiredTokenException extends AuthenticationException {

    private JwtToken token;

    public JwtExpiredTokenException(String msg) {
        super(msg);
    }

    public JwtExpiredTokenException(JwtToken token, String msg, Throwable t) {
        super(msg, t);
        this.token = token;
    }

    public String token() {
        return this.token.getToken();
    }
}
