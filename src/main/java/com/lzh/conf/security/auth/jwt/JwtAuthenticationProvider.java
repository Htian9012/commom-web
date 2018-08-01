package com.lzh.conf.security.auth.jwt;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.lzh.conf.security.model.UserContext;
import com.lzh.conf.security.model.token.RawAccessJwtToken;
import com.lzh.conf.security.properties.JwtProperties;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

/**
 * Token验证实现
 *
 * @author Acris Liu
 */
@Component
@SuppressWarnings("unchecked")
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private final JwtProperties jwtProperties;

    @Autowired
    public JwtAuthenticationProvider(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        RawAccessJwtToken rawAccessToken = (RawAccessJwtToken) authentication.getCredentials();

        Jws<Claims> jwsClaims = rawAccessToken.parseClaims(jwtProperties.getTokenSigningKey());
        String username = jwsClaims.getBody().getSubject();

        List<String> scopes = jwsClaims.getBody().get("scopes", List.class);
        List<GrantedAuthority> authorities = scopes.stream().map(authority -> new SimpleGrantedAuthority(authority)).collect(toList());

        UserContext context = UserContext.create(username, authorities);

        return new JwtAuthenticationToken(context, context.getAuthorities());
    }

    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
