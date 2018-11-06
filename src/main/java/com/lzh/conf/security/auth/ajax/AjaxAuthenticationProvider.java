package com.lzh.conf.security.auth.ajax;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.lzh.conf.security.auth.UserAuthenticationService;
import com.lzh.conf.security.model.UserContext;
import com.lzh.mybatis.entity.MscUser;

/**
 * Ajax登录实现类
 *
 * @author htian
 */
@Component
public class AjaxAuthenticationProvider implements AuthenticationProvider {
    private static final Logger log = LoggerFactory.getLogger(AjaxAuthenticationProvider.class);
    private final BCryptPasswordEncoder encoder;
    private final UserAuthenticationService userAuthenticationService;

    @Autowired
    public AjaxAuthenticationProvider(final BCryptPasswordEncoder encoder, final UserAuthenticationService userAuthenticationService) {
        this.encoder = encoder;
        this.userAuthenticationService = userAuthenticationService;
    }
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.notNull(authentication, "未找到认证信息");
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        MscUser user = userAuthenticationService.getUser(username);

        if (!encoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("用户名或密码不正确");
        }

        UserContext userContext = userAuthenticationService.getUseContext(username);
        return new UsernamePasswordAuthenticationToken(userContext, null, userContext.getAuthorities());
    }

    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
