package com.lzh.conf.security.exceptions;

import org.springframework.security.authentication.AuthenticationServiceException;

/**
 * 认证方式不受支持错误类
 *
 * @author htian
 */
public class AuthMethodNotSupportedException extends AuthenticationServiceException {
    public AuthMethodNotSupportedException(String msg) {
        super(msg);
    }
}
