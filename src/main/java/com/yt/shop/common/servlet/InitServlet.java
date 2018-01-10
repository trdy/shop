package com.yt.shop.common.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * anthor:liyun
 * create:2017-11-24 13:04
 */
@WebServlet(urlPatterns="/servlet/initServlet",loadOnStartup = 1)
public class InitServlet extends HttpServlet{

    private Logger log= LoggerFactory.getLogger(this.getClass());

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        log.info("init servlet 加载初始化参数");
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("execute servlet test......");
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        out.print("test init servlet");
    }

}
