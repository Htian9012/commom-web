package com.lzh.conf.security.model;

import com.google.common.base.Strings;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * 用户上下文对象
 *
 * @author htian
 */
public class UserContext {
    private final String username;
    private final List<GrantedAuthority> authorities;

    private UserContext(String username, List<GrantedAuthority> authorities) {
        this.username = username;
        this.authorities = authorities;
    }

    public static UserContext create(String username, List<GrantedAuthority> authorities) {
        if (Strings.isNullOrEmpty(username)) throw new IllegalArgumentException("用户名为空");
        return new UserContext(username, authorities);
    }

    public String getUsername() {
        return username;
    }

    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
