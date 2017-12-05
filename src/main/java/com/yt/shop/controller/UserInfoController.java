package com.yt.shop.controller;

import com.yt.shop.common.Constract;
import com.yt.shop.common.MD5;
import com.yt.shop.common.VerifyCodeUtils;
import com.yt.shop.model.OperRecord;
import com.yt.shop.model.UserInfo;
import com.yt.shop.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class UserInfoController {

    private Logger log= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 生成图形验证码
     * */
    @RequestMapping("/valiCode")
    public void authImage(HttpServletRequest request, HttpServletResponse response)throws IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        //生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        //存入会话session
        HttpSession session = request.getSession(true);
        //删除以前的
        session.removeAttribute(Constract.VERIFY_CODE);
        session.setAttribute(Constract.VERIFY_CODE, verifyCode.toLowerCase());
        //生成图片
        int w = 100, h = 40;
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
    }

    /**
     * 后台登录验证
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/admin/validUser",method= RequestMethod.POST)
    @ResponseBody
    public int validUser(HttpServletRequest request) throws IOException{
        String userName=request.getParameter("userName");
        String userPass=request.getParameter("userPass");
        String checkcode=request.getParameter("checkcode");

        log.info("验证用户登陆信息。。。");
        HttpSession session=request.getSession();
        String verifyCode=(String) session.getAttribute(Constract.VERIFY_CODE);
        if(null!=verifyCode&&verifyCode.equalsIgnoreCase(checkcode)){
            UserInfo userInfo=userInfoService.findBankUserByNameAndPass(userName, MD5.GetMD5Code(userPass));
            if(userInfo!=null){
                session.setAttribute(Constract.ADMIN_LOGIN_FLAG, userInfo);
                log.info("登录成功。。。。:"+userInfo);
                return 1;
            }else{
                log.info("用户名密码不正确");
                return -1;
            }
        }else{
            log.info("验证码不正确");
            return -2;
        }
    }
}
