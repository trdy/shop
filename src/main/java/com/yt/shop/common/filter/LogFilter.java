package com.yt.shop.common.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * anthor:liyun
 * create:2017-11-24 13:13
 */

public class LogFilter  implements Filter {

    private Logger log= LoggerFactory.getLogger(this.getClass());


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //log.info("test execute filter....init ");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


    }

    @Override
    public void destroy() {
        log.info("test execute filter destroy");
    }
}
