package com.yt.shop.controller;

import com.yt.shop.common.FileUtil;
import com.yt.shop.model.Person;
import com.yt.shop.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class DemoController {

    private Logger log= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DemoService demoService;


    @RequestMapping("/person/savePerson")
    @ResponseBody
    public int savePerson(@RequestBody Person person){
        log.info("测试保存person的方法");
        try {
            Person p = demoService.insertPerson(person);
            log.debug("保存person对象成功");
            return 1;
        }catch (Exception e){
            log.error("保存person对象出错："+e.toString());
            return 0;
        }

    }

    @RequestMapping("/person/listPerson")
    @ResponseBody
    public List<Person> listPerson(){
        log.info("测试查询person集合的方法");
        try {
            List<Person> list = demoService.findPersonList();
            log.debug("查询person集合成功");
            return list;
        }catch (Exception e){
            log.error("查询person集合出错:"+e.getMessage());
            return new ArrayList<Person>();
        }

    }


    @RequestMapping("/person/personUpload")
    public String personUpload(){
        return "personUpload";
    }

    @RequestMapping("/person/savePerson1")
    @ResponseBody
    public int savePerson1(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        log.info("call 文件上传");
        int result=0;
        try {
            Person p = new Person();
            String pname = request.getParameter("pname");
            p.setPname(pname);
            p.setBirth(new Timestamp(System.currentTimeMillis()));

            if(!file.isEmpty()) {
                String filePath = System.getProperty("user.dir") + "/upload/userHead/";
                int n = file.getOriginalFilename().indexOf(".");
                String fileName = UUID.randomUUID() + file.getOriginalFilename().substring(n);
                //String fileName="abc.jpg";
                log.info("上传文件目录：" + filePath);

                try {
                    FileUtil.uploadFile(file.getBytes(), filePath, fileName);
                    p.setPimg("/upload/userHead/" + fileName);
                    log.info("上传成功");
                    result=1;
                } catch (Exception e) {
                    log.error("执行文件上传：" + e.toString());

                }
            }else {
                p.setPimg("/upload/userHead/defaultHead.jpg");
            }

            demoService.insertPerson(p);
            log.debug("文件上传成功,数据正确保存："+p);
            return result;
        }catch (Exception e){
            log.error("文件上传错误"+e.getMessage());
            return result;
        }

    }




}
