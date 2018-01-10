package com.yt.shop.common;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.UUID;

/**
 * anthor:liyun
 * create:2017-11-23 10:16
 */
public class FileUtil {
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+"/"+fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    public static String uploadFile(MultipartFile file, String rootPath) throws Exception {
        String fileName=file.getOriginalFilename();
        int p=fileName.lastIndexOf(".");
        String newFileName=UUID.randomUUID()+fileName.substring(p);
        File fileDir=new File(rootPath);
        if(!fileDir.exists()){
            fileDir.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(rootPath+"/"+newFileName);
        out.write(file.getBytes());
        out.flush();
        out.close();
        return newFileName;
    }

    public static String uploadBase64File(String base64Data,String uploadPath) throws Exception {
        //如果上传的路径不存在就创建目录
        File uploadDir=new File(uploadPath);
        if(!uploadDir.exists()){
            uploadDir.mkdirs();
        }

        String dataPrix = "";
        String data = "";
        if(base64Data == null || "".equals(base64Data)){
            throw new Exception("上传失败，上传图片数据为空");
        }else{
            String [] d = base64Data.split("base64,");
            if(d != null && d.length == 2){
                dataPrix = d[0];
                data = d[1];
            }else{
                throw new Exception("上传失败，数据不合法");
            }
        }
        String suffix = "";
        if("data:image/jpeg;".equalsIgnoreCase(dataPrix)){//data:image/jpeg;base64,base64编码的jpeg图片数据
            suffix = ".jpg";
        } else if("data:image/x-icon;".equalsIgnoreCase(dataPrix)){//data:image/x-icon;base64,base64编码的icon图片数据
            suffix = ".ico";
        } else if("data:image/gif;".equalsIgnoreCase(dataPrix)){//data:image/gif;base64,base64编码的gif图片数据
            suffix = ".gif";
        } else if("data:image/png;".equalsIgnoreCase(dataPrix)){//data:image/png;base64,base64编码的png图片数据
            suffix = ".png";
        }else{
            throw new Exception("上传图片格式不合法");
        }
        String tempFileName = UUID.randomUUID().toString() + suffix;

        //因为BASE64Decoder的jar问题，此处使用spring框架提供的工具包
        byte[] bs = Base64Utils.decodeFromString(data);
        try{
            //使用apache提供的工具类操作流
            FileOutputStream out = new FileOutputStream(uploadPath+"/"+tempFileName);
            out.write(bs);
            out.flush();
            out.close();
        }catch(Exception ee){
            throw new Exception("上传失败，写入文件失败，"+ee.getMessage());
        }
        return tempFileName;
    }

    public static String loadBase64File(String filePath) {
        try {
            String loadFilePath = System.getProperty("user.dir") + "/" + filePath;

            FileInputStream fin = new FileInputStream(loadFilePath);
            byte[] buf = new byte[fin.available()];
            fin.read(buf);
            String base64Str=Base64Utils.encodeToString(buf);
            return base64Str;
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }


    public static String formatNewsContent(String content) throws Exception {
        Document doc=  Jsoup.parse(content);
        Elements elements=doc.getElementsByTag("img");

        String rootPath=System.getProperty("user.dir");
        String filePath="/upload/news/"+DateUtil.getYearMonth();
        for(Element element:elements){
            String fileCode=element.attr("src");
            if(fileCode.startsWith("data:image")){
                String fileName=FileUtil.uploadBase64File(fileCode,rootPath+filePath);
                element.attr("src","http://192.168.1.201:8081/"+filePath+"/"+fileName);
                //element.attr("src",Cache.httpPrefix+filePath+"/"+fileName);
            }
        }
        String html=doc.body().html();
        return html;
    }



}
