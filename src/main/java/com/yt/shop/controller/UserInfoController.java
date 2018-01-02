package com.yt.shop.controller;

import com.yt.shop.common.*;
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
import org.springframework.web.bind.annotation.RequestParam;
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
     *     http://192.168.1.201:8081/valiCode
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
     *     请求地址：http://192.168.1.201:8081/admin/validUser
     *     请求方式：post
     *     请求参数：
     *          userName:用户名
     *          userPass:用户密码
     *          checkCode：图形验证码上的文字
     *</pre>
     * 回应内容：
     * <pre>
     *     json字符串
     *          {"code":1,"message":"登录成功","data":""}
     *          {"code":-1,"message":"用户名或密码不正确","data":""}
     *          {"code":-2,"message":"验证码不正确","data":""}
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
                return JsonUtil.getReturnJson(1,"登录成功");
            }else{
                log.info("用户名密码不正确");
                return JsonUtil.getReturnJson(-1,"用户名或密码不正确");
            }
        }else{
            log.info("验证码不正确");
            return JsonUtil.getReturnJson(-2,"验证码不正确");
        }
    }

    /**
     * 个人中心--&gt;后台用户查看个人信息
     * @param request 请求对象
     * @return 个人信息 json字符串
     *
     *<p>&nbsp;</p>
     * 请求格式：
     * <pre>
     *     请求地址：http://192.168.1.201:8081/admin/userInfoEdit
     *     请求方式：get
     *     请求参数：无
     *</pre>
     * 回应内容：
     * <pre>
     *     json字符串
     *          {"code":1,"message":"正确获取当前后台操作人员信息",
     *              "data":{"code":"420400197402111013",
     *                      "zengzhiScore":0.0,
     *                      "tuiguanScore":0.0,
     *                      "gouwuScore":0.0,
     *                      "xianjinScore":0.0,
     *                      "duihuanScore":0.0,
     *                      "userName":"admin",
     *                      "userPass":"c4ca4238a0b923820dcc509a6f75849b",
     *                      "headPic":"/upload/userHead/defaultHead.jpg",
     *                      "userId":1,
     *                      "regTime":1513217876000,
     *                      "userState":1,
     *                      "shopCars":[],
     *                      "name":"admin",
     *                      "question":"1+1=?",
     *                      "answer":"10",
     *                      "userType":{"userTypeId":10,"permissions":[],"userTypeName":"管理员"},
     *                      "email":"371866295@qq.com"},
     *                      "refuserId":""}
     *          {"code":-1,"message":"用户名未登录"}
     * </pre>
     * <table border="1">
     *      <caption>userInfo-json对象属性</caption>
     *  <tr><td>参数</td><td>含义</td><td>备注</td></tr>
     *  <tr><td>userId</td><td>会员编号</td><td></td></tr>
     *  <tr><td>userName</td><td>会员登录账号</td><td>&nbsp;</td></tr>
     *  <tr><td>userPass</td><td>会员登录密码</td><td>&nbsp;</td></tr>
     *  <tr><td>name</td><td>会员真实姓名</td><td>&nbsp;</td></tr>
     *  <tr><td>code</td><td>会员身份证号</td><td></td></tr>
     *  <tr><td>regTime</td><td>会员注册时间</td><td></td></tr>
     *  <tr><td>headPic</td><td>会员头像</td><td></td></tr>
     *  <tr><td>question</td><td>提示问题</td><td></td></tr>
     *  <tr><td>answer</td><td>提示答案</td><td></td></tr>
     *  <tr><td>userState</td><td>会员状态</td><td>1：正常，0：冻结</td></tr>
     *  <tr><td>userType.userTypeId</td><td>会员类型编号</td><td></td></tr>
     *  <tr><td>userType.userName</td><td>会员类型名称</td><td></td></tr>
     *  <tr><td>email</td><td>邮件地址</td><td></td></tr>
     *  <tr><td>shopCars</td><td>会员购物车数据</td><td></td></tr>
     *  <tr><td>tuiguanScore</td><td>推广积分</td><td></td></tr>
     *  <tr><td>gouwuScore</td><td>购物积分</td><td></td></tr>
     *  <tr><td>xianjinScore</td><td>现金积分</td><td></td></tr>
     *  <tr><td>duihuanScore</td><td>兑换积分</td><td></td></tr>
     *  <tr><td>zengzhiScore</td><td>增值积分</td><td></td></tr>
     *  <tr><td>refuserId</td><td>推荐人id</td><td></td></tr>
     *  </table>
     */
    @RequestMapping(value = "/admin/userInfoEdit",method = RequestMethod.GET)
    @ResponseBody
    public String backUserInfoEdit(HttpServletRequest request){
        log.info("后台用户查看个人信息");
        UserInfo user= (UserInfo) request.getSession().getAttribute(Constract.ADMIN_LOGIN_FLAG);
        operRecordJpa.save(new OperRecord(user,request.getRemoteAddr(),user.getUserName()+"后台用户查看个人信息"));

        UserInfo userInfo=userInfoService.findUserInfoById(user.getUserId());
        return  JsonUtil.getReturnJson(1,"正确获取当前后台操作人员信息",userInfo);
    }

    /**
     * 个人中心--&gt;后台用户修改个人信息保存操作
     * @param headPic 用户头像 上传图片 base64编码字符串
     * @param request 请求对象，包含 email,question,answer
     * @return 是否保存成功
     *
     *<p>&nbsp;</p>
     * 请求格式：
     * <pre>
     *     请求地址：http://192.168.1.201:8081/admin/userInfoSave
     *     请求方式：post
     *     请求参数：
     *          email:用户名
     *          question:用户密码
     *          answer：图形验证码上的文字
     *          headPic:上传新头像base编码
     *          oldHeadPic:原有旧头像路径地址
     *</pre>
     * 回应内容：
     * <pre>
     *     json字符串
     *          {"code":1,"message":"保存用户数据成功"}
     *          {"code":-1,"message":"用户未登录"}
     *          {"code":-2,"message":"保存用户数据出错"}
     * </pre>
     */
    @RequestMapping(value = "/admin/userInfoSave",method = RequestMethod.POST)
    @ResponseBody
    public String backUserInfoSave(@RequestParam(required = false) String headPic, HttpServletRequest request){
        log.info("后台用户修改并保存个人信息");
        UserInfo user= (UserInfo) request.getSession().getAttribute(Constract.ADMIN_LOGIN_FLAG);
        operRecordJpa.save(new OperRecord(user,request.getRemoteAddr(),user.getUserName()+"后台用户修改并保存个人信息"));

        try {
            UserInfo userInfo = userInfoService.findUserInfoById(user.getUserId());

            String email=request.getParameter("email");
            String question=request.getParameter("question");
            String answer=request.getParameter("answer");

            if(null!=email&&!"".equals(email)){
                userInfo.setEmail(email);
            }
            if(null!=question&&!"".equals(question)){
                userInfo.setQuestion(question);
            }
            if(null!=answer&&!"".equals(answer)){
                userInfo.setAnswer(answer);
            }

            //更改用户头像
            if (null != headPic && !"".equals(headPic)) {
                String uploadPath = System.getProperty("user.dir") + "/upload/userHead/" + DateUtil.getYearMonth();
                log.info("上传文件保存到" + uploadPath);
                String fileName = FileUtil.uploadBase64File(headPic, uploadPath);
                userInfo.setHeadPic("/upload/userHead/" + DateUtil.getYearMonth() + "/" + fileName);
            } else {
                String oldHeadPic = request.getParameter("oldHeadPic");
                userInfo.setHeadPic(oldHeadPic);
            }

            userInfoService.saveUserInfo(userInfo);
            return JsonUtil.getReturnJson(1,"保存用户数据成功");
        }catch (Exception e){
            e.printStackTrace();
            return JsonUtil.getReturnJson(-2,"保存用户数据出错");
        }
    }


    /**
     * 个人中心--&gt;后台用户修改密码
     * @param request 请求对象
     * @return 修改密码结果
     *<p>&nbsp;</p>
     * 请求格式：
     * <pre>
     *     请求地址：http://192.168.1.201:8081/admin/userInfoUpdatePass
     *     请求方式：post
     *     请求参数：
     *          oldPass:旧密码
     *          newPass:新密码
     *          repass：二次输入新密码
     *</pre>
     * 回应内容：
     * <pre>
     *     json字符串
     *          {"code":1,"message":"修改密码成功"}
     *          {"code":-1,"message":"用户未登录"}
     *          {"code":-2,"message":"原有旧密码不正确,不能修改"}
     *          {"code":-3,"message":"修改的新密码与旧密码相同，不能修改"}
     *          {"code":-4,"message":"两次输入的新密码不一致,不能修改"}
     * </pre>
     */
    @RequestMapping(value = "/admin/userInfoUpdatePass",method = RequestMethod.POST)
    @ResponseBody
    public String backUserInfoUpdatePass(HttpServletRequest request){
        log.info("后台用户修改密码");
        UserInfo user= (UserInfo) request.getSession().getAttribute(Constract.ADMIN_LOGIN_FLAG);
        operRecordJpa.save(new OperRecord(user,request.getRemoteAddr(),user.getUserName()+"后台用户修改密码"));

        String oldPass=request.getParameter("oldPass");
        String newPass=request.getParameter("newPass");
        String rePass=request.getParameter("rePass");

        if(!user.getUserPass().equals(MD5.GetMD5Code(oldPass))){
            return JsonUtil.getReturnJson(-2,"原有旧密码不正确,不能修改!");
        }
        if(oldPass.equals(newPass)){
            return JsonUtil.getReturnJson(-3,"修改的新密码与旧密码相同，不能修改！");
        }
        if(!newPass.equals(rePass)){
            return JsonUtil.getReturnJson(-4,"两次输入的新密码不一致,不能修改！");
        }
        UserInfo userInfo=userInfoService.findUserInfoById(user.getUserId());
        userInfo.setUserPass(MD5.GetMD5Code(newPass));
        userInfoService.saveUserInfo(userInfo);
        return JsonUtil.getReturnJson(1,"修改密码成功");
    }

    /**
     * 个人中心--&gt;后台用户退出系统
     * @param request 请求对象
     * @return 返回退出结果
     *
     *<p>&nbsp;</p>
     * 请求格式：
     * <pre>
     *     请求地址：http://192.168.1.201:8081/admin/userInfoExit
     *     请求方式：get
     *     请求参数：无
     *</pre>
     * 回应内容：
     * <pre>
     *     json字符串
     *          {"code":1,"message":"正常退出系统"}
     *          {"code":-1,"message":"用户未登录"}
     *          {"code":-2,"message":"未登录无需退出"}
     * </pre>
     */
    @RequestMapping(value = "/admin/userInfoExit",method = RequestMethod.GET)
    @ResponseBody
    public String backUserInfoExit(HttpServletRequest request){
        log.info("后台用户退出系统");
        UserInfo user= (UserInfo) request.getSession().getAttribute(Constract.ADMIN_LOGIN_FLAG);
        if(null!=user) {
            operRecordJpa.save(new OperRecord(user, request.getRemoteAddr(), user.getUserName() + "后台用户修改密码"));
            request.getSession().removeAttribute(Constract.ADMIN_LOGIN_FLAG);
            return JsonUtil.getReturnJson(1,"正常退出系统");
        }else{
            return JsonUtil.getReturnJson(-2,"未登录无需退出");
        }
    }

}
