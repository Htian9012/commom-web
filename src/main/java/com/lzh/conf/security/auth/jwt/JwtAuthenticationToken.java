package com.lzh.conf.security.auth.jwt;


import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import com.lzh.conf.security.model.UserContext;
import com.lzh.conf.security.model.token.RawAccessJwtToken;

/**
 * JWT Token对象
 *
 * @author Acris Liu
 */
public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = 1L;

    private RawAccessJwtToken rawAccessToken;
    private UserContext userContext;

    public JwtAuthenticationToken(RawAccessJwtToken unsafeToken) {
        super(null);
        this.rawAccessToken = unsafeToken;
        this.setAuthenticated(false);
    }

    public JwtAuthenticationToken(UserContext userContext, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.eraseCredentials();
        this.userContext = userContext;
        super.setAuthenticated(true);
    }

    
    public void setAuthenticated(boolean authenticated) {
        if (authenticated) {
            throw new IllegalArgumentException("请使用构造方法设置该属性");
        }
        super.setAuthenticated(false);
    }

    
    public Object getCredentials() {
        return rawAccessToken;
    }

    
    public Object getPrincipal() {
        return this.userContext;
    }

    
    public void eraseCredentials() {
        super.eraseCredentials();
        this.rawAccessToken = null;
    }
}
