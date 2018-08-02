package com.lzh.conf.security.auth.jwt.verifier;

import org.springframework.stereotype.Component;

/**
 * Refresh Token验证器
 *
 * @author htian
 */
@Component
public class RefreshTokenVerifier {
    public boolean verify(String jti) {
        //暂不实现，如果是有效期很长的refresh token，则可以使用黑名单机制，手动吊销refresh token,然后在此处判断refresh token的jti是否在黑名单中。
        return true;
    }
}
