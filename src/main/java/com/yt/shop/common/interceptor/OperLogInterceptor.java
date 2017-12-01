package com.yt.shop.common.interceptor;

import com.yt.shop.common.Constract;
import com.yt.shop.dao.OperRecordDao;
import com.yt.shop.dao.OperRecordJpa;
import com.yt.shop.model.OperRecord;
import com.yt.shop.model.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;


public class OperLogInterceptor implements HandlerInterceptor{

    private Logger log= LoggerFactory.getLogger(this.getClass());

    @Autowired
    OperRecordJpa operRecordJpa;

    //@Autowired
    //OperRecordDao operRecordDao;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        log.info(httpServletRequest.getRequestURI());

        HttpSession session=httpServletRequest.getSession();
        UserInfo userInfo= (UserInfo) session.getAttribute(Constract.ADMIN_LOGIN_FLAG);
        if(null==userInfo){
            userInfo=new UserInfo(-1L);
        }

        log.debug("拦截检查当前用户："+userInfo);

        HandlerMethod method= (HandlerMethod) o;
        String ipaddr=httpServletRequest.getRemoteAddr();
        String operClass=method.getBean().getClass().getName();
        String operMethod=method.getMethod().getName();

        OperRecord operRecord=new OperRecord();
        operRecord.setUserInfo(userInfo);
        operRecord.setIpAddress(ipaddr);
        operRecord.setOperContent("call "+operClass+"->"+operMethod);
        operRecord.setOperDate(new Timestamp(System.currentTimeMillis()));

        log.debug("操作日志对象："+operRecord);

        //operRecordDao.insertOperRecord(operRecord);
        operRecordJpa.save(operRecord);
        log.debug("拦截器记录操作到数据库。。");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
