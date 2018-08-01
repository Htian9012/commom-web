package com.lzh.conf.security.utils;

import javax.servlet.http.HttpServletRequest;

import com.lzh.conf.security.auth.jwt.JwtAuthenticationToken;
import com.lzh.conf.security.model.UserContext;

/**
 * 登录工具类
 *
 * @author Acris Liu
 */
public class AuthUtils {
    private static final String XML_HTTP_REQUEST = "XMLHttpRequest";
    private static final String X_REQUESTED_WITH = "X-Requested-With";

    /**
     * 判断是否为Ajax请求
     *
     * @param request
     * @return 是或否
     */
    public static boolean isAjax(HttpServletRequest request) {
        return XML_HTTP_REQUEST.equals(request.getHeader(X_REQUESTED_WITH));
    }

    /**
     * 获取当前用户的用户名和权限
     *
     * @param token
     * @return UserContext
     */
    public static UserContext getCurrentUser(JwtAuthenticationToken token) {
        return (UserContext) token.getPrincipal();
    }
}
