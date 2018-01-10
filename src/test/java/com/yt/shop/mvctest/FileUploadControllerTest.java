package com.yt.shop.mvctest;

import com.yt.shop.controller.FileUploadController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileUploadControllerTest {

    @Test
    public void contextLoads() {
    }

    private MockMvc mockMvc; // 模拟MVC对象，通过MockMvcBuilders.webAppContextSetup(this.wac).build()初始化。

    @Autowired
    private WebApplicationContext wac; // 注入WebApplicationContext

    @Before // 在测试开始前初始化工作
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Autowired
    FileUploadController fileUploadController;

    @Test
    public void testNewsFileupload() throws Exception {

        FileInputStream fis = new FileInputStream("D:\\shoplogo.png");
        MockMultipartFile mfile = new MockMultipartFile("newsImg","shoplogo.png","image/jpeg", fis);
        MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
        request.addFile(mfile);
        request.setMethod("POST");
        request.setContentType("multipart/form-data");
        request.addHeader("Content-type", "multipart/form-data");

        String json=fileUploadController.newsFileupload(mfile);
        System.out.println(json);
        Assert.assertTrue(true);
    }

    /*@Test
    public void testNewsFileupload1() throws Exception {

        FileInputStream fis = new FileInputStream("D:\\shoplogo.png");
        MockMultipartFile mfile = new MockMultipartFile("newsImg","shoplogo.png","image/jpeg", fis);
        MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
        MockHttpServletResponse response=new MockHttpServletResponse();
        request.addFile(mfile);
        request.setMethod("POST");
        request.setContentType("multipart/form-data");
        request.addHeader("Content-type", "multipart/form-data");

        String json=fileUploadController.newsFileupload1(request,response);
        System.out.println(json);
        Assert.assertTrue(true);
    }*/

    @Test
    public void testBannerFileupload() throws Exception {

        FileInputStream fis = new FileInputStream("D:\\shoplogo.png");
        MockMultipartFile mfile = new MockMultipartFile("bannerImg","shoplogo.png","image/jpeg", fis);
        MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
        request.addFile(mfile);
        request.setMethod("POST");
        request.setContentType("multipart/form-data");
        request.addHeader("Content-type", "multipart/form-data");

        String json=fileUploadController.bannerFileupload(mfile);
        System.out.println(json);
        Assert.assertTrue(true);
    }
}
