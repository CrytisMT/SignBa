package com.maitaidan.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Crytis on 2016/3/14.
 * Just test.
 */
public class LoginFilter implements Filter {
    private static Logger logger = LoggerFactory.getLogger(LoginFilter.class);
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String requestURI = httpServletRequest.getRequestURI();
        logger.info("请求的url:",requestURI);
        chain.doFilter(request,response);
    }

    public void destroy() {

    }
}
