package com.yt.shop.mvctest;

import com.alibaba.fastjson.JSONObject;
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
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
     * @throws Exception 异常对象
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
                .andExpect(status().isOk())// 模拟向testRest发送get请求
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();// 返回执行请求的结果


        System.out.println("输出结果："+result.getResponse().getContentAsString());
    }

    @Autowired
    SiteBaseInfoController siteBaseInfoController;

    /**
     * 测试保存商城基本信息
     * @throws Exception 异常对象
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

    /**
     * 测试后台管理返回轮播图列表信息
     * @throws Exception 异常对象
     */
    @Test
    public void testBackShopBanner() throws Exception{
        UserInfo userInfo=new UserInfo();
        userInfo.setUserId(1L);
        userInfo.setUserName("admin");


        MvcResult result = mockMvc.perform(get("/admin/shopBanner")
                .sessionAttr(Constract.ADMIN_LOGIN_FLAG,userInfo)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())// 模拟向testRest发送get请求
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();// 返回执行请求的结果


        System.out.println("输出结果："+result.getResponse().getContentAsString());
    }

    /**
     * 测试转到轮播图编辑
     * @throws Exception 异常对象
     */
    @Test
    public void testBackShopBannerEdit() throws Exception{
        UserInfo userInfo=new UserInfo();
        userInfo.setUserId(1L);
        userInfo.setUserName("admin");


        MvcResult result = mockMvc.perform(get("/admin/shopBanner/1")
                .sessionAttr(Constract.ADMIN_LOGIN_FLAG,userInfo)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())// 模拟向testRest发送get请求
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();// 返回执行请求的结果


        System.out.println("输出结果："+result.getResponse().getContentAsString());
    }

    /**
     * 测试轮播图保存
     * @throws Exception 异常对象
     */
    @Test
    public void testbackShopBannerSave() throws Exception{

        FileInputStream fis = new FileInputStream("d:\\login.gif");
        MockMultipartFile file = new MockMultipartFile("file","1.gif","image/jpeg",fis);

        //定义一个请求对象
        MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
        request.setMethod("POST");
        request.setContentType("multipart/form-data");
        request.addHeader("Content-type", "multipart/form-data");

        //设置请求参数
        request.setParameter("banid","2");
        request.setParameter("bannerUrl","#");
        request.setParameter("bannerDesc","宣传2");
        //设置上传文件
        request.addFile(file);

        //测试controller上传的方法
        siteBaseInfoController.backShopBannerSave(file,request);
        Assert.assertTrue(true);
    }

    /**
     * 测试后台管理员删除轮播图
     * @throws Exception 异常对象
     */
    @Test
    public void testBackShopBannerDel() throws Exception{
        UserInfo userInfo=new UserInfo();
        userInfo.setUserId(1L);
        userInfo.setUserName("admin");

        MvcResult result = mockMvc.perform(delete("/admin/shopBanner/2")
                .sessionAttr(Constract.ADMIN_LOGIN_FLAG,userInfo)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())// 模拟向testRest发送delete请求
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();// 返回执行请求的结果


        System.out.println("输出结果："+result.getResponse().getContentAsString());
    }

    /**
     * 测试后台管理员查询会员类型列表
     * @throws Exception 异常对象
     */
    @Test
    public void testBackUserTypeList() throws Exception{
        UserInfo userInfo=new UserInfo();
        userInfo.setUserId(1L);
        userInfo.setUserName("admin");


        MvcResult result = mockMvc.perform(get("/admin/userType")
                .sessionAttr(Constract.ADMIN_LOGIN_FLAG,userInfo)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())// 模拟向testRest发送get请求
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();// 返回执行请求的结果


        System.out.println("输出结果："+result.getResponse().getContentAsString());
    }

    /**
     * 测试进入会员类型编辑，如果是新增，id设为0，如果是修改，按照id查询待修改的会员类型
     * @throws Exception 异常对象
     */
    @Test
    public void testBackUserTypeEdit() throws Exception{
        UserInfo userInfo=new UserInfo();
        userInfo.setUserId(1L);
        userInfo.setUserName("admin");


        MvcResult result = mockMvc.perform(get("/admin/userType/10")
                .sessionAttr(Constract.ADMIN_LOGIN_FLAG,userInfo)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())// 模拟向testRest发送get请求
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();// 返回执行请求的结果


        System.out.println("输出结果："+result.getResponse().getContentAsString());
    }

    /**
     * 测试会员类型保存
     * @throws Exception 异常对象
     */
    @Test
    public void testBackUserTypeSave() throws Exception{
        UserInfo userInfo=new UserInfo();
        userInfo.setUserId(1L);
        userInfo.setUserName("admin");

        Map<String,Object> map=new HashMap<>();
        map.put("userTypeId",101);
        map.put("userTypeName","另类用户1");

        MvcResult result = mockMvc.perform(post("/admin/userType")
                .sessionAttr(Constract.ADMIN_LOGIN_FLAG,userInfo)
                .content(JSONObject.toJSONString(map))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())// 模拟向testRest发送post请求
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();// 返回执行请求的结果


        System.out.println("输出结果："+result.getResponse().getContentAsString());
    }

    /**
     * 测试删除会员类型
     * @throws Exception 异常对象
     */
    @Test
    public void testBackUserTypeDel() throws Exception{
        UserInfo userInfo=new UserInfo();
        userInfo.setUserId(1L);
        userInfo.setUserName("admin");

        MvcResult result = mockMvc.perform(delete("/admin/userType/101")
                .sessionAttr(Constract.ADMIN_LOGIN_FLAG,userInfo)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())// 模拟向testRest发送delete请求
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();// 返回执行请求的结果


        System.out.println("输出结果："+result.getResponse().getContentAsString());
    }
}
