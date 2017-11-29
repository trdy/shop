package com.yt.shop.mvctest;

import com.alibaba.fastjson.JSONObject;
import com.yt.shop.ShopApplication;
import com.yt.shop.controller.DemoController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.naming.Context;
import javax.servlet.ServletContext;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonControllerTest {

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

    @Test
    public void testSave() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("pname", "张三");
        map.put("birth", System.currentTimeMillis());
        map.put("pimg", "/upload/userHead/aaa.jpg");

        MvcResult result = mockMvc.perform(post("/person/savePerson")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONObject.toJSONString(map)))
                .andExpect(status().isOk())// 模拟向testRest发送post请求
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果


        System.out.println("输出结果："+result.getResponse().getContentAsString());
    }

    @Test
    public void testFindPersonList() throws Exception{
        MvcResult result = mockMvc.perform(get("/person/listPerson")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())// 模拟向testRest发送get请求
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();
        System.out.println("输出结果："+result.getResponse().getContentAsString());
    }

    @Autowired
    DemoController demoController;

    @Test
    public void testSave1() throws Exception{
        //测试文件上传，上传d盘下login.gif
        FileInputStream fis = new FileInputStream("d:\\login.gif");
        MockMultipartFile file = new MockMultipartFile("file","1.gif","image/jpeg",fis);

        //定义一个请求对象
        MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
        request.setMethod("POST");
        request.setContentType("multipart/form-data");
        request.addHeader("Content-type", "multipart/form-data");

        //设置请求参数
        request.setParameter("pname","aaaaa");
        //设置上传文件
        request.addFile(file);

        //测试controller上传的方法
        demoController.savePerson1(file,request);
        Assert.assertTrue(true);

    }


}
