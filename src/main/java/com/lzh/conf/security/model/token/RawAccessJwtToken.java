package com.lzh.conf.security.model.token;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;

import com.lzh.conf.security.exceptions.JwtExpiredTokenException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * 原始Access Jwt Token对象
 *
 * @author htian
 */
public class RawAccessJwtToken implements JwtToken {
    private static Logger log = LoggerFactory.getLogger(RawAccessJwtToken.class);

    private String token;

    public RawAccessJwtToken(String token) {
        this.token = token;
    }

    /**
     * 解析Token
     *
     * @throws BadCredentialsException
     * @throws JwtExpiredTokenException
     */
    public Jws<Claims> parseClaims(String signingKey) {
        try {
            return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token);
        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | SignatureException ex) {
            log.error("无效的Refresh Token", ex);
            throw new BadCredentialsException("无效的Refresh Token");
        } catch (ExpiredJwtException expiredEx) {
            log.error("Refresh Token已过期", expiredEx);
            throw new JwtExpiredTokenException("Refresh Token已过期");
        }
    }

    @Override
    public String getToken() {
        return token;
    }
}
