package com.yt.shop.controller;

import com.yt.shop.common.Constract;
import com.yt.shop.common.MD5;
import com.yt.shop.common.VerifyCodeUtils;
import com.yt.shop.dao.OperRecordJpa;
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
import java.util.HashMap;
import java.util.Map;

/**
 * 用户登录验证控制器
 */
@Controller
public class UserInfoController {

    private Logger log= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OperRecordJpa operRecordJpa;

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 生成图形验证码
     * @param request 请求对象
     * @param response 回应对象
     * @throws IOException
     *
     * 用户登录系统，未防止用户试探登录，生成图形验证码
     *<p>&nbsp;</p>
     * 请求格式：
     * <pre>
     *     http://127.0.0.1:8081/valiCode
     *</pre>
     * 回应内容：
     * <pre>
     *     验证码图片
     * </pre>
     */
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
     * @param request 请求对象
     * @return  返回json字符串
     * @throws IOException
     *
     *<p>&nbsp;</p>
     * 请求格式：
     * <pre>
     *     请求地址：http://127.0.0.1:8081/admin/validUser
     *     请求方式：post
     *     请求参数：
     *          userName:用户名
     *          userPass:用户密码
     *          checkCode：图形验证码上的文字
     *</pre>
     * 回应内容：
     * <pre>
     *     json字符串
     *          {"code":1} 登录成功
     *          {"code":-1} 用户名或密码不正确
     *          {\"code\":-2} 验证码不正确
     * </pre>
     */
    @RequestMapping(value = "/admin/validUser",method =RequestMethod.POST)
    @ResponseBody
    public String validUser(HttpServletRequest request) throws IOException{
        String userName=request.getParameter("userName");
        String userPass=request.getParameter("userPass");
        String checkCode=request.getParameter("checkCode");

        Map<String,Object> map=new HashMap<>();
        log.info("验证用户登陆信息。。。");
        HttpSession session=request.getSession();
        String verifyCode=(String) session.getAttribute(Constract.VERIFY_CODE);
        if(null!=verifyCode&&verifyCode.equalsIgnoreCase(checkCode)){
            UserInfo userInfo=userInfoService.findBackUserByNameAndPass(userName, MD5.GetMD5Code(userPass));
            if(userInfo!=null){
                session.setAttribute(Constract.ADMIN_LOGIN_FLAG, userInfo);
                log.info("登录成功。。。。:"+userInfo);
                //记录操作日志
                operRecordJpa.save(new OperRecord(userInfo,request.getRemoteAddr(),userName+"登录成功后台管理"));
                return "{\"code\":1}";
            }else{
                log.info("用户名密码不正确");
                return "{\"code\":-1}";
            }
        }else{
            log.info("验证码不正确");
            return "{\"code\":-2}";
        }
    }

}
