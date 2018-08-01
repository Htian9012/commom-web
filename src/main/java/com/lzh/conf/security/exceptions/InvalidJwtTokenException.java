package com.lzh.conf.security.exceptions;

/**
 * 无效Token错误类
 *
 * @author Acris Liu
 */
public class InvalidJwtTokenException extends RuntimeException {
    public InvalidJwtTokenException(String msg) {
        super(msg);
    }
}
