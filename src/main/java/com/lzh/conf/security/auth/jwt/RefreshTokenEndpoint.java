package com.lzh.conf.security.auth.jwt;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lzh.conf.security.auth.UserAuthenticationService;
import com.lzh.conf.security.auth.jwt.extractor.TokenExtractor;
import com.lzh.conf.security.auth.jwt.verifier.RefreshTokenVerifier;
import com.lzh.conf.security.config.WebSecurityConfig;
import com.lzh.conf.security.exceptions.InvalidJwtTokenException;
import com.lzh.conf.security.model.UserContext;
import com.lzh.conf.security.model.token.JwtToken;
import com.lzh.conf.security.model.token.JwtTokenFactory;
import com.lzh.conf.security.model.token.RawAccessJwtToken;
import com.lzh.conf.security.model.token.RefreshToken;
import com.lzh.conf.security.properties.JwtProperties;

/**
 * 刷新Token接口
 *
 * @author htian
 */
@RestController
public class RefreshTokenEndpoint {
    private final JwtTokenFactory tokenFactory;
    private final JwtProperties jwtProperties;
    private final RefreshTokenVerifier tokenVerifier;
    private final TokenExtractor tokenExtractor;
    private final UserAuthenticationService userAuthenticationService;

    @Autowired
    public RefreshTokenEndpoint(JwtTokenFactory tokenFactory, JwtProperties jwtProperties, RefreshTokenVerifier tokenVerifier, TokenExtractor tokenExtractor, UserAuthenticationService userAuthenticationService) {
        this.tokenFactory = tokenFactory;
        this.jwtProperties = jwtProperties;
        this.tokenVerifier = tokenVerifier;
        this.tokenExtractor = tokenExtractor;
        this.userAuthenticationService = userAuthenticationService;
    }

    @RequestMapping(value = "/api/auth/token", method = RequestMethod.GET)
    public JwtToken refreshToken(HttpServletRequest request) {
        String tokenPayload = tokenExtractor.extract(request.getHeader(WebSecurityConfig.JWT_TOKEN_HEADER_PARAM));

        RawAccessJwtToken rawToken = new RawAccessJwtToken(tokenPayload);
        RefreshToken refreshToken = RefreshToken.create(rawToken, jwtProperties.getTokenSigningKey()).orElseThrow(() -> new InvalidJwtTokenException("Refresh Token不合法"));

        String jti = refreshToken.getJti();
        if (!tokenVerifier.verify(jti)) {
            throw new InvalidJwtTokenException("Refresh Token验证失败");
        }

        //更新用户权限
        String username = refreshToken.getSubject();
        UserContext userContext = userAuthenticationService.getUseContext(username);

        return tokenFactory.createAccessJwtToken(userContext);
    }
}
