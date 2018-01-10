package com.yt.shop.controller;

import com.alibaba.fastjson.JSONObject;
import com.yt.shop.common.DateUtil;
import com.yt.shop.common.FileUtil;
import com.yt.shop.common.JsonUtil;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

/***
 * 文件上传控制器
 */
@Controller
public class FileUploadController {

    private Logger log= LoggerFactory.getLogger(this.getClass());

    @Value("${http.prefix}")
    private String httpPrefix;


    /**
     * 新闻图片上传控制器
     * @param file 上传图片文件域 参数名 newsImg
     * @return 上传结果 json字符串
     *
     * *<p>&nbsp;</p>
     * 请求格式：
     * <pre>
     *     请求地址：http://192.168.1.201:8081/admin/newsFileUpload
     *     请求方式：post
     *     请求参数：文件域名称 newsImg
     *</pre>
     * 回应内容：
     *      {"code":1,"message":"新闻图片上传成功","data":"/upload/news/201801/aaa.jpg"}
     *      {"code":-2,"message":"新闻图片上传失败"}
     */
    @RequestMapping(value = "/admin/newsFileUpload", method = RequestMethod.POST)
    @ResponseBody
    public String newsFileupload(@RequestParam(value="newsImg",required = false) MultipartFile file) {
        try {

            if(null==file){
                return JsonUtil.getReturnJson(-4,"上传新闻图片为空");
            }
            String uploadPath="/upload/news/"+ DateUtil.getYearMonth(); //定义文件上下文路径
            String rootPath = System.getProperty("user.dir") +uploadPath ; //定义文件存储绝对路径
            log.info("上传新闻图片保存到"+rootPath);
            String fileName= FileUtil.uploadFile(file,rootPath);
            log.info("上传新闻图片保存成功"+fileName);
            return JsonUtil.getReturnJson(1,"新闻图片上传成功",uploadPath+"/"+fileName);
        }catch (Exception e){
            e.printStackTrace();
            log.error("新闻图片上传失败");
            return JsonUtil.getReturnJson(-2,"新闻图片上传失败");
        }
    }


    /**
     * 首页轮播图图片上传控制器
     * @param file 上传图片文件域 参数名 bannerImg
     * @return 上传结果 json字符串
     *
     * *<p>&nbsp;</p>
     * 请求格式：
     * <pre>
     *     请求地址：http://192.168.1.201:8081/admin/bannerFileUpload
     *     请求方式：post
     *     请求参数：文件域名称 bannerImg
     *</pre>
     * 回应内容：
     *      {"code":1,"message":"首页轮播图图片上传成功","data":"/upload/banner/aaa.jpg"}
     *      {"code":-2,"message":"首页轮播图图片上传失败"}
     */
    @RequestMapping(value = "/admin/bannerFileUpload", method = RequestMethod.POST)
    @ResponseBody
    public String bannerFileupload(@RequestParam(value="bannerImg",required = false) MultipartFile file) {
        try {

            if(null==file){
                return JsonUtil.getReturnJson(-4,"上传首页轮播图图片为空");
            }
            String uploadPath="/upload/banner"; //定义文件上下文路径
            String rootPath = System.getProperty("user.dir") +uploadPath ; //定义文件存储绝对路径
            log.info("上传首页轮播图图片保存到"+rootPath);
            String fileName= FileUtil.uploadFile(file,rootPath);
            log.info("上传首页轮播图图片保存成功"+fileName);
            return JsonUtil.getReturnJson(1,"首页轮播图图片上传成功",uploadPath+"/"+fileName);
        }catch (Exception e){
            e.printStackTrace();
            log.error("首页轮播图图片上传失败");
            return JsonUtil.getReturnJson(-2,"首页轮播图图片上传失败");
        }
    }

    /*@RequestMapping(value = "/file/newsFileUpload1", method = RequestMethod.POST)
    @ResponseBody
    public String newsFileupload1(MultipartHttpServletRequest request,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        try {
            String uploadPath = "/upload/news/" + DateUtil.getYearMonth(); //定义文件上下文路径
            String rootPath = System.getProperty("user.dir") + uploadPath; //定义文件存储绝对路径
            String out="";
            List<MultipartFile> files = request.getFiles("newsImg");
            if(files.size()<=0){
                return JsonUtil.getReturnJson(-4,"上传新闻图片为空");
            }
            for(MultipartFile file:files) {
                log.info("上传新闻图片保存到" + rootPath);
                String fileName = FileUtil.uploadFile(file, rootPath);
                log.info("上传新闻图片保存成功" + fileName);
                out+=uploadPath+"/"+fileName;
            }

            return JsonUtil.getReturnJson(1,"新闻图片上传成功",out);
        }catch (Exception e){
            e.printStackTrace();
            log.error("新闻图片上传失败");
            return JsonUtil.getReturnJson(-2,"新闻图片上传失败");
        }
    }*/


    ///------------------------------暂时留着，文件上传套路整好就可以删除啦------------------------------

    @RequestMapping(value = "/test/file_upload", method = RequestMethod.POST)
    @ResponseBody
    public void fileUpload(MultipartHttpServletRequest request,
                           HttpServletResponse response) throws ServletException, IOException,FileUploadException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("P3P","CP=CAO PSA OUR");

        //文件保存目录路径
        //String savePath = request.getServletContext().getRealPath("/") + "attached/";
        String savePath = System.getProperty("user.dir")+"/upload/attached/";
        //文件保存目录URL
        //String saveUrl  = request.getContextPath() + "/upload/attached/";
        String saveUrl  = httpPrefix+"/upload/attached/";

        //定义允许上传的文件扩展名
        HashMap<String, String> extMap = new HashMap<String, String>();
        extMap.put("image", "gif,jpg,jpeg,png,bmp");
        extMap.put("flash", "swf,flv");
        extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
        extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

        //最大文件大小
        long maxSize = 10000000;

        response.setContentType("text/html; charset=UTF-8");
        //response.setContentType("application/json");

        PrintWriter out=response.getWriter();
        if(!ServletFileUpload.isMultipartContent(request)){
            out.println(getError("请选择文件。"));
            return;
        }
        //检查目录
        File uploadDir = new File(savePath);
        if(!uploadDir.isDirectory()){
            //out.println(getError("上传目录不存在。"));
            //return;
            uploadDir.mkdirs();
        }
        //检查目录写权限
        if(!uploadDir.canWrite()){
            out.println(getError("上传目录没有写权限。"));
            return;
        }

        String dirName = request.getParameter("dir");
        if (dirName == null) {
            dirName = "image";
        }
        if(!extMap.containsKey(dirName)){
            out.println(getError("目录名不正确。"));
            return;
        }
        //创建文件夹
        savePath += dirName + "/";
        saveUrl += dirName + "/";
        File saveDirFile = new File(savePath);
        if (!saveDirFile.exists()) {
            saveDirFile.mkdirs();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String ymd = sdf.format(new Date());
        savePath += ymd + "/";
        saveUrl += ymd + "/";
        File dirFile = new File(savePath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }

        List<MultipartFile> files = request.getFiles("imgFile");
        Iterator<MultipartFile> itr = files.iterator();
        while (itr.hasNext()) {
            MultipartFile item = itr.next();
            String fileName = item.getOriginalFilename();

            if(item.getSize() > maxSize){
                out.println(getError("上传文件大小超过限制。"));
                return;
            }

            //检查扩展名
            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
            if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
                out.println(getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));
                return;
            }

            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
            try{
                File uploadedFile = new File(savePath, newFileName);
                item.transferTo(uploadedFile);
            }catch(Exception e){
                out.println(getError("上传文件失败。"));
                return;
            }

            JSONObject obj = new JSONObject();
            obj.put("error", 0);
            obj.put("url", saveUrl + newFileName);
            log.info("上传文件返回json:"+obj.toJSONString());
            out.println(obj.toJSONString());
        }
    }

    private String getError(String message) {
        JSONObject obj = new JSONObject();
        obj.put("error", 1);
        obj.put("message", message);
        return obj.toJSONString();
    }


}
