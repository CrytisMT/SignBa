package com.maitaidan.filter;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Crytis on 2016/3/14.
 * Just test.
 */
public class LoginFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(LoginFilter.class);
    private Pattern[] patterns;

    public void init(FilterConfig filterConfig) throws ServletException {
        String excluds = filterConfig.getInitParameter("excluds");
        logger.info("排除:{}", excluds);
        if (StringUtils.isBlank(excluds)) {
            return;
        }

        String[] exclude = excluds.split(",");
        patterns = new Pattern[exclude.length];
        for (int i = 0; i < exclude.length; i++) {
            Pattern compiledExclude = Pattern.compile(transStringToPattern(exclude[i]));
            patterns[i] = compiledExclude;
        }

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String requestURI = httpServletRequest.getRequestURI();
        if (!isExclude(requestURI)) {
            Cookie[] cookies = httpServletRequest.getCookies();

            if (!ArrayUtils.isEmpty(cookies)) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("singpf")) {
                        if ("test".equals(cookie.getValue())) {
                            logger.info("url:{}已登录！", httpServletRequest.getRequestURI());
                            break;
                        } else {
                            logger.info("url:{}未登录！", httpServletRequest.getRequestURI());
                            httpServletResponse.sendRedirect("/noauth.html");
                            return;
                        }
                    }
                }
            } else {
                logger.info("url:{}未登录！", httpServletRequest.getRequestURI());
                httpServletResponse.sendRedirect("/noauth.html");
                return;
            }
        }
        chain.doFilter(request, response);
    }


    private boolean isExclude(String url) {
        if (StringUtils.isBlank(url)) {
            return true;
        }

        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(url);
            if (matcher.matches()) {
                return true;
            }
        }

        return false;
    }

    protected String transStringToPattern(String string) {
        String result;
        result = string.replaceAll("\\.", "\\\\.");
        result = result.replaceAll("\\*", ".*");
        return result;
    }

    public void destroy() {

    }
}
