package com.lzh.conf.security.auth.jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.lzh.conf.security.exceptions.JwtExpiredTokenException;

/**
 * 登录失败错误捕获
 *
 * @author Acris Liu
 */
@Component
public class JwtAuthenticationFailureHandler implements AuthenticationFailureHandler {

    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        String failureMessage;
        int httpCode = HttpStatus.UNAUTHORIZED.value();
        if (e instanceof BadCredentialsException) {
            failureMessage = "Token无效";
        } else if (e instanceof JwtExpiredTokenException) {
            failureMessage = "Token已过期";
            httpCode = HttpStatus.OK.value();
        } else {
            failureMessage = "Token认证失败";
        }
        response.sendError(httpCode, failureMessage);
    }
}
