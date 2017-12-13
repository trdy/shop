package com.yt.shop.controller;

import com.yt.shop.common.FileUtil;
import com.yt.shop.common.MD5;
import com.yt.shop.model.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * anthor:liyun
 * create:2017-11-22 12:09
 */
@Controller
public class TestController {

    private static final Logger log =  LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TestService testService;

    /**
     * 测试静态网页
     * @return
     */
    @RequestMapping("/test")
    public String test(){
        log.info("call TestController test method ......");
        return "test/test";
    }

    /**
     * 测试thymeleaf模板
     * @param map
     * @return
     */
    @RequestMapping("/test2")
    public String test2(Model map) {
        log.info("call TestController test2 method.......");
        map.addAttribute("msg","测试thymeleaf");
        return "test/test2";
    }



    /**
     * 测试异常处理
     * @return
     */
    @RequestMapping("/testError")
    @ResponseBody
    public String testError(){
        int n=1/0;
        return "success";
    }

    /**
     * 测试文件上传
     * @param file
     * @param request
     * @return
     */
    //处理文件上传
    @RequestMapping(value="/testuploadimg", method = RequestMethod.POST)
    public @ResponseBody String uploadImg(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        if (!file.isEmpty()) {
            String contentType = file.getContentType();
            int p = file.getOriginalFilename().indexOf(".");
            String fileName = UUID.randomUUID() + file.getOriginalFilename().substring(p);
            log.info("contentType" + contentType + "->fileName:" + fileName);

            String filePath = System.getProperty("user.dir")+"/upload/goodsPic/";
            log.info("上传文件目录："+filePath);
            try {
                FileUtil.uploadFile(file.getBytes(), filePath, fileName);
                log.info("上传成功");
                return "upload img success";
            } catch (Exception e) {
                log.error("执行文件上传：" + e.toString());
                return "upload img failure";
            }
        } else{
            log.info("no choose upload file");
            return "no choose upload file";
        }
    }

    /**
     * 转到template目录下的login.html
     * @return
     */
    @RequestMapping("/login")
    public String toLogin(){
        return "login";
    }

    /**
     * 测试jpa数据库操作
     * @param userName
     * @param userPass
     * @return
     */
    @RequestMapping("/user/login")
    @ResponseBody
    public int login(String userName,String userPass){
        log.info("验证用户名:"+userName+"和密码："+userPass);
        UserInfo userInfo=testService.findUserInfoByNameAndPass(userName, MD5.GetMD5Code(userPass));
        if(userInfo!=null){
            log.info("查询数据库返回userinfo:"+userInfo);
            return 1;
        }else{
            log.info("查询数据库未找到");
            return 0;
        }

    }


}
