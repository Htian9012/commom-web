package com.lzh.conf.security.config;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lzh.conf.security.auth.RestAuthenticationEntryPoint;
import com.lzh.conf.security.auth.ajax.AjaxAuthenticationProvider;
import com.lzh.conf.security.auth.ajax.AjaxAwareAuthenticationFailureHandler;
import com.lzh.conf.security.auth.ajax.AjaxAwareAuthenticationSuccessHandler;
import com.lzh.conf.security.auth.ajax.AjaxLoginProcessingFilter;
import com.lzh.conf.security.auth.jwt.JwtAuthenticationFailureHandler;
import com.lzh.conf.security.auth.jwt.JwtAuthenticationProvider;
import com.lzh.conf.security.auth.jwt.JwtTokenAuthenticationProcessingFilter;
import com.lzh.conf.security.auth.jwt.SkipPathRequestMatcher;
import com.lzh.conf.security.auth.jwt.extractor.TokenExtractor;
import com.lzh.conf.security.properties.CORSProperties;
import com.lzh.conf.security.properties.TokenProperties;

/**
 * Spring Security配置类
 *
 * @author htian
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final Logger log = LoggerFactory.getLogger(WebSecurityConfig.class);

    public static final String JWT_TOKEN_HEADER_PARAM = "X-Authorization";
    public static final String JWT_TOKEN_HEADER_PREFIX = "Bearer ";

    private RestAuthenticationEntryPoint unauthorizedHandler;
    private AjaxAwareAuthenticationSuccessHandler ajaxSuccessHandler;
    private AjaxAwareAuthenticationFailureHandler ajaxFailureHandler;
    private JwtAuthenticationFailureHandler jwtFailureHandler;
    private AjaxAuthenticationProvider ajaxAuthenticationProvider;
    private JwtAuthenticationProvider jwtAuthenticationProvider;
    private TokenExtractor tokenExtractor;
    private AuthenticationManager authenticationManager;
    private ObjectMapper objectMapper;
    private TokenProperties tokenProperties;
    private CORSProperties corsProperties;

    @Autowired
    public void setUnauthorizedHandler(RestAuthenticationEntryPoint unauthorizedHandler) {
        this.unauthorizedHandler = unauthorizedHandler;
    }

    @Autowired
    public void setAjaxSuccessHandler(AjaxAwareAuthenticationSuccessHandler ajaxSuccessHandler) {
        this.ajaxSuccessHandler = ajaxSuccessHandler;
    }

    @Autowired
    public void setAjaxFailureHandler(AjaxAwareAuthenticationFailureHandler ajaxFailureHandler) {
        this.ajaxFailureHandler = ajaxFailureHandler;
    }

    @Autowired
    public void setJwtFailureHandler(JwtAuthenticationFailureHandler jwtFailureHandler) {
        this.jwtFailureHandler = jwtFailureHandler;
    }

    @Autowired
    public void setAjaxAuthenticationProvider(AjaxAuthenticationProvider ajaxAuthenticationProvider) {
        this.ajaxAuthenticationProvider = ajaxAuthenticationProvider;
    }

    @Autowired
    public void setJwtAuthenticationProvider(JwtAuthenticationProvider jwtAuthenticationProvider) {
        this.jwtAuthenticationProvider = jwtAuthenticationProvider;
    }

    @Autowired
    public void setTokenExtractor(TokenExtractor tokenExtractor) {
        this.tokenExtractor = tokenExtractor;
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Autowired
    public void setTokenProperties(TokenProperties tokenProperties) {
        this.tokenProperties = tokenProperties;
    }

    @Autowired
    public void setCorsProperties(CORSProperties corsProperties) {
        this.corsProperties = corsProperties;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //关闭csrf
        http.csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                .and()
                //使用无状态Session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //设置资源认证策略
                .authorizeRequests()
                //放行登录接口
                .antMatchers(tokenProperties.getLoginEntryPoint()).permitAll()
                //放行刷新Token接口
                .antMatchers(tokenProperties.getRefreshEntryPoint()).permitAll()
                //保护需要认证的路径
                .antMatchers(tokenProperties.getAuthEntryPoint().split("\\s*,\\s*")).authenticated()
                //放行其他路径
                .anyRequest().permitAll()
                .and()
                //启用跨域
                .cors()
                .and()
                //登录请求的过滤器
                .addFilterBefore(buildAjaxLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
                //Token认证的过滤器
                .addFilterBefore(buildJwtTokenAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(ajaxAuthenticationProvider);
        auth.authenticationProvider(jwtAuthenticationProvider);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * CORS跨域配置
     *
     * @return
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(corsProperties.getAllowedOrigins().split("\\s*,\\s*")));
        configuration.setAllowedMethods(Arrays.asList(corsProperties.getAllowedMethods().split("\\s*,\\s*")));
        configuration.setAllowedHeaders(Arrays.asList(corsProperties.getAllowedHeaders().split("\\s*,\\s*")));
        configuration.setAllowCredentials(corsProperties.getAllowCredentials());
        configuration.setMaxAge(corsProperties.getMaxAge());
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(corsProperties.getMapping(), configuration);
        return source;
    }

    /**
     * Ajax登录请求处理
     *
     * @return
     * @throws Exception
     */
    protected AjaxLoginProcessingFilter buildAjaxLoginProcessingFilter() throws Exception {
        AjaxLoginProcessingFilter filter = new AjaxLoginProcessingFilter(tokenProperties.getLoginEntryPoint(), ajaxSuccessHandler, ajaxFailureHandler, objectMapper);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }

    /**
     * Token认证请求处理
     *
     * @return
     * @throws Exception
     */
    protected JwtTokenAuthenticationProcessingFilter buildJwtTokenAuthenticationProcessingFilter() throws Exception {
        //跳过登录和刷新token的接口
        List<String> pathsToSkip = Arrays.asList(tokenProperties.getLoginEntryPoint(), tokenProperties.getRefreshEntryPoint());
        SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip, tokenProperties.getAuthEntryPoint().split("\\s*,\\s*"));
        JwtTokenAuthenticationProcessingFilter filter = new JwtTokenAuthenticationProcessingFilter(jwtFailureHandler, tokenExtractor, matcher);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }

}
