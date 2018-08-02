package com.lzh.conf.security.auth.jwt;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.Assert;

/**
 * 需要跳过的资源匹配器
 *
 * @author htian
 */
public class SkipPathRequestMatcher implements RequestMatcher {
    private OrRequestMatcher matcher;
    private List<RequestMatcher> processingMatcherList;

    public SkipPathRequestMatcher(List<String> pathsToSkip, String... processingPaths) {
        Assert.notNull(pathsToSkip, "没有要跳过的资源");
        List<RequestMatcher> matcherList = pathsToSkip.stream().map(AntPathRequestMatcher::new).collect(toList());
        matcher = new OrRequestMatcher(matcherList);
        processingMatcherList = Arrays.stream(processingPaths).map(AntPathRequestMatcher::new).collect(toList());
    }

    public boolean matches(HttpServletRequest request) {
        return !matcher.matches(request) && processingMatcherList.stream().anyMatch(processingMatcher -> processingMatcher.matches(request));
    }
}
