package com.yt.shop.mvctest;

import com.yt.shop.common.Constract;
import com.yt.shop.controller.SiteBaseInfoController;
import com.yt.shop.model.UserInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.FileInputStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SiteBaseInfoControllerTest {

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

    /**
     * 测试网站基本信息设置，获取商城名称基本信息
     * @throws Exception
     */
    @Test
    public void testShopBaseInfoSet() throws Exception {

        UserInfo userInfo=new UserInfo();
        userInfo.setUserId(1L);
        userInfo.setUserName("admin");


        MvcResult result = mockMvc.perform(get("/admin/shopInfoSet")
                .sessionAttr(Constract.ADMIN_LOGIN_FLAG,userInfo)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())// 模拟向testRest发送post请求
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果


        System.out.println("输出结果："+result.getResponse().getContentAsString());
    }

    @Autowired
    SiteBaseInfoController siteBaseInfoController;

    /**
     * 测试保存商城基本信息
     * @throws Exception
     */
    @Test
    public void testShopBaseInfoSave()throws Exception{
        FileInputStream fis = new FileInputStream("d:\\login.gif");
        MockMultipartFile file = new MockMultipartFile("file","1.gif","image/jpeg",fis);

        //定义一个请求对象
        MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
        request.setMethod("POST");
        request.setContentType("multipart/form-data");
        request.addHeader("Content-type", "multipart/form-data");

        //设置请求参数
        request.setParameter("nsid","1");
        request.setParameter("nsname","创福网");
        //设置上传文件
        request.addFile(file);

        //测试controller上传的方法
        siteBaseInfoController.shopBaseInfoSave(file,request);
        Assert.assertTrue(true);

    }
}
