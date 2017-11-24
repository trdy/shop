package com.yt.shop.common.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * anthor:liyun
 * create:2017-11-24 13:13
 */
@WebFilter(filterName="testFilter",urlPatterns="/*")
public class TestFilter implements Filter {

    private Logger log= LoggerFactory.getLogger(this.getClass());
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("test execute filter....init ");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("test execute filter doFilter");
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        log.info("请求地址："+request.getRequestURI());
        filterChain.doFilter(servletRequest,servletResponse);

    }

    @Override
    public void destroy() {
        log.info("test execute filter destroy");
    }
}
