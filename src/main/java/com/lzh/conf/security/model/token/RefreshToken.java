package com.lzh.conf.security.model.token;


import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.BadCredentialsException;

import com.lzh.conf.security.model.Scopes;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

/**
 * Refresh Token对象
 *
 * @author Acris Liu
 */
@SuppressWarnings("unchecked")
public class RefreshToken implements JwtToken {
    private Jws<Claims> claims;

    private RefreshToken(Jws<Claims> claims) {
        this.claims = claims;
    }

    /**
     * 创建并校验Refresh Token
     *
     * @param token      用户Token
     * @param signingKey 签名
     * @return
     * @throws BadCredentialsException
     */
    public static Optional<RefreshToken> create(RawAccessJwtToken token, String signingKey) {
        Jws<Claims> claims = token.parseClaims(signingKey);

        List<String> scopes = claims.getBody().get("scopes", List.class);

        //通过判断scope中是否存在REFRESH_TOKEN来确定token类型
        if (scopes == null || scopes.isEmpty() || !scopes.stream().filter(scope -> Scopes.REFRESH_TOKEN.authority().equals(scope)).findFirst().isPresent()) {
            return Optional.empty();
        }

        return Optional.of(new RefreshToken(claims));
    }

    @Override
    public String getToken() {
        return null;
    }

    public Jws<Claims> getClaims() {
        return claims;
    }

    public String getJti() {
        return claims.getBody().getId();
    }

    public String getSubject() {
        return claims.getBody().getSubject();
    }
}
