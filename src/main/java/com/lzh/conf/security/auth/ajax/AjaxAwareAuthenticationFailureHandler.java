package com.lzh.conf.security.auth.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.lzh.conf.security.exceptions.AuthMethodNotSupportedException;

/**
 * 登录失败错误捕获
 *
 * @author htian
 */
@Component
public class AjaxAwareAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        String failureMessage;
        if (e instanceof BadCredentialsException) {
            failureMessage = "用户名或密码错误";
        } else if (e instanceof AccountExpiredException) {
            failureMessage = "账号已过期";
        } else if (e instanceof LockedException) {
            failureMessage = "账号被锁定";
        } 
        else if (e instanceof AuthMethodNotSupportedException) {
            failureMessage = "不支持该登录方式";
        } 
        else {
            failureMessage = "未知原因登录失败";
        }
        response.sendError(HttpStatus.UNAUTHORIZED.value(), failureMessage);
    }
}
