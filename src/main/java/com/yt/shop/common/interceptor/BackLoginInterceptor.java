package com.yt.shop.common.interceptor;

import com.yt.shop.common.Constract;
import com.yt.shop.model.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BackLoginInterceptor extends HandlerInterceptorAdapter {

    private Logger log= LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //log.info("验证是否登录拦截检查--> 访问地址："+httpServletRequest.getRequestURI());
        HttpSession session=httpServletRequest.getSession();
        UserInfo userInfo= (UserInfo) session.getAttribute(Constract.ADMIN_LOGIN_FLAG);
        if(null==userInfo){
            httpServletResponse.setContentType(String.valueOf(MediaType.APPLICATION_JSON_UTF8));
            httpServletResponse.getWriter().print("{\"code\",-1}");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
