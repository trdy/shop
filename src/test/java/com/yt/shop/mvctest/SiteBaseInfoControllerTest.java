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
import org.springframework.util.Base64Utils;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
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

    //--------------------------------------------------

    /**
     * 测试网站基本信息设置，获取商城名称基本信息
     *
     * @throws Exception 异常对象
     */
    @Test
    public void testShopBaseInfoSet() throws Exception {

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(1L);
        userInfo.setUserName("admin");


        MvcResult result = mockMvc.perform(get("/admin/shopInfo")
                .sessionAttr(Constract.ADMIN_LOGIN_FLAG, userInfo)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();// 返回执行请求的结果


        System.out.println("输出结果：" + result.getResponse().getContentAsString());
    }

    @Autowired
    SiteBaseInfoController siteBaseInfoController;

    /**
     * 测试保存商城基本信息
     *
     * @throws Exception 异常对象
     */
    @Test
    public void testShopBaseInfoSave() throws Exception {
        FileInputStream fis = new FileInputStream("d:\\login.jpg");
        byte[] buf = new byte[fis.available()];
        fis.read(buf);
        fis.close();
        String base64Code = Base64Utils.encodeToString(buf);

        System.out.println(base64Code);
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(1L);
        userInfo.setUserName("admin");


        MvcResult result = mockMvc.perform(post("/admin/shopInfo")
                .sessionAttr(Constract.ADMIN_LOGIN_FLAG, userInfo)
                .param("nslogo", "data:image/jpeg;base64," + base64Code)
                .param("nsname", "创福网")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();// 返回执行请求的结果


        System.out.println("输出结果：" + result.getResponse().getContentAsString());

    }

    //--------------------------------------------------

    /**
     * 测试后台管理返回轮播图列表信息
     *
     * @throws Exception 异常对象
     */
    @Test
    public void testBackShopBannerList() throws Exception {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(1L);
        userInfo.setUserName("admin");


        MvcResult result = mockMvc.perform(get("/admin/shopBannerList")
                .sessionAttr(Constract.ADMIN_LOGIN_FLAG, userInfo)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();// 返回执行请求的结果


        System.out.println("输出结果：" + result.getResponse().getContentAsString());
    }

    /**
     * 测试转到轮播图编辑
     *
     * @throws Exception 异常对象
     */
    @Test
    public void testBackShopBannerEdit() throws Exception {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(1L);
        userInfo.setUserName("admin");


        MvcResult result = mockMvc.perform(get("/admin/shopBannerEdit")
                .sessionAttr(Constract.ADMIN_LOGIN_FLAG, userInfo)
                .param("shopBannerId","0")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();// 返回执行请求的结果


        System.out.println("输出结果：" + result.getResponse().getContentAsString());
    }

    /**
     * 测试轮播图保存
     *
     * @throws Exception 异常对象
     */
    @Test
    public void testbackShopBannerSave() throws Exception {

        /*FileInputStream fis = new FileInputStream("d:\\login.gif");
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
        Assert.assertTrue(true);*/

        FileInputStream fis = new FileInputStream("d:\\shoplogo.png");
        byte[] buf = new byte[fis.available()];
        fis.read(buf);
        String base64Code = Base64Utils.encodeToString(buf);

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(1L);
        userInfo.setUserName("admin");

        /**
         * data:image/jpeg;base64,
         * data:image/png;base64,
         * data:image/x-icon;base64,
         * data:image/gif;base64,
         */
        MvcResult result = mockMvc.perform(post("/admin/shopBannerSave")
                .sessionAttr(Constract.ADMIN_LOGIN_FLAG, userInfo)
                .param("bannerFile", "data:image/png;base64," + base64Code)
                .param("bannerUrl", "#")
                .param("bannerDesc", "...")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();// 返回执行请求的结果


        System.out.println("输出结果：" + result.getResponse().getContentAsString());
    }

    /**
     * 测试后台管理员删除轮播图
     *
     * @throws Exception 异常对象
     */
    @Test
    public void testBackShopBannerDel() throws Exception {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(1L);
        userInfo.setUserName("admin");

        MvcResult result = mockMvc.perform(post("/admin/shopBannerDel")
                .param("shopBannerId","1")
                .sessionAttr(Constract.ADMIN_LOGIN_FLAG, userInfo)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();// 返回执行请求的结果


        System.out.println("输出结果：" + result.getResponse().getContentAsString());
    }

    //--------------------------------------------------

    /**
     * 测试后台管理员查询会员类型列表
     *
     * @throws Exception 异常对象
     */
    @Test
    public void testBackUserTypeList() throws Exception {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(1L);
        userInfo.setUserName("admin");


        MvcResult result = mockMvc.perform(get("/admin/userTypeList")
                .sessionAttr(Constract.ADMIN_LOGIN_FLAG, userInfo)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();// 返回执行请求的结果


        System.out.println("输出结果：" + result.getResponse().getContentAsString());
    }

    /**
     * 测试进入会员类型编辑，如果是新增，id设为0，如果是修改，按照id查询待修改的会员类型
     *
     * @throws Exception 异常对象
     */
    @Test
    public void testBackUserTypeEdit() throws Exception {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(1L);
        userInfo.setUserName("admin");


        MvcResult result = mockMvc.perform(get("/admin/userTypeEdit")
                .sessionAttr(Constract.ADMIN_LOGIN_FLAG, userInfo)
                .param("userTypeId","102")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();// 返回执行请求的结果


        System.out.println("输出结果：" + result.getResponse().getContentAsString());
    }

    /**
     * 测试会员类型保存
     *
     * @throws Exception 异常对象
     */
    @Test
    public void testBackUserTypeSave() throws Exception {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(1L);
        userInfo.setUserName("admin");

        Map<String, Object> map = new HashMap<>();
        map.put("userTypeId", 102);
        map.put("userTypeName", "另类用户1111");

        MvcResult result = mockMvc.perform(post("/admin/userTypeSave")
                .sessionAttr(Constract.ADMIN_LOGIN_FLAG, userInfo)
                .content(JSONObject.toJSONString(map))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();// 返回执行请求的结果


        System.out.println("输出结果：" + result.getResponse().getContentAsString());
    }

    /**
     * 测试删除会员类型
     *
     * @throws Exception 异常对象
     */
    @Test
    public void testBackUserTypeDel() throws Exception {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(1L);
        userInfo.setUserName("admin");

        MvcResult result = mockMvc.perform(post("/admin/userTypeDel")
                .sessionAttr(Constract.ADMIN_LOGIN_FLAG, userInfo)
                .param("userTypeId","102")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())//
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();// 返回执行请求的结果


        System.out.println("输出结果：" + result.getResponse().getContentAsString());
    }

    //--------------------------------------------------
    @Test
    public void testBackGoodsPlateList() throws Exception{
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(1L);
        userInfo.setUserName("admin");

        MvcResult result = mockMvc.perform(get("/admin/goodsPlateList")
                .sessionAttr(Constract.ADMIN_LOGIN_FLAG, userInfo)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())//
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();// 返回执行请求的结果


        System.out.println("输出结果：" + result.getResponse().getContentAsString());
    }

    /**
     * 测试商品板块编辑
     * @throws Exception 异常对象
     */
    @Test
    public void testBackGoodsPlateEdit() throws Exception{
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(1L);
        userInfo.setUserName("admin");

        MvcResult result = mockMvc.perform(get("/admin/goodsPlateEdit")
                .sessionAttr(Constract.ADMIN_LOGIN_FLAG, userInfo)
                .param("goodsPlateId","7")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();// 返回执行请求的结果


        System.out.println("输出结果：" + result.getResponse().getContentAsString());
    }

    /**
     * 测试商品板块保存
     * @throws Exception 异常对象
     */
    @Test
    public void testBackGoodsPlateSave() throws Exception{
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(1L);
        userInfo.setUserName("admin");

        Map<String,Object> map=new HashMap<>();
        map.put("gpid",7);
        map.put("gpname","军火11");
        map.put("gpremark","");

        MvcResult result = mockMvc.perform(post("/admin/goodsPlateSave")
                .sessionAttr(Constract.ADMIN_LOGIN_FLAG, userInfo)
                .content(JSONObject.toJSONString(map))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();// 返回执行请求的结果


        System.out.println("输出结果：" + result.getResponse().getContentAsString());
    }


    /**
     * 测试商品板块删除
     * @throws Exception 异常对象
     */
    @Test
    public void testBackGoodsPlateDel() throws Exception{
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(1L);
        userInfo.setUserName("admin");


        MvcResult result = mockMvc.perform(delete("/admin/goodsPlateDel")
                .param("goodsPlateId","7")
                .sessionAttr(Constract.ADMIN_LOGIN_FLAG, userInfo)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();// 返回执行请求的结果


        System.out.println("输出结果：" + result.getResponse().getContentAsString());
    }

    /**
     * 测试商品类别列表查询
     * @throws Exception 异常对象
     */
    @Test
    public void testBackGoodsTypeList() throws Exception{
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(1L);
        userInfo.setUserName("admin");


        MvcResult result = mockMvc.perform(get("/admin/goodsTypeList")
                .sessionAttr(Constract.ADMIN_LOGIN_FLAG, userInfo)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();// 返回执行请求的结果


        System.out.println("输出结果：" + result.getResponse().getContentAsString());
    }

    /**
     * 测试商品类别编辑
     * @throws Exception 异常对象
     */
    @Test
    public void testBackGoodsTypeEdit() throws Exception{
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(1L);
        userInfo.setUserName("admin");


        MvcResult result = mockMvc.perform(get("/admin/goodsTypeEdit")
                .param("goodsTypeId","1")
                .sessionAttr(Constract.ADMIN_LOGIN_FLAG, userInfo)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();// 返回执行请求的结果


        System.out.println("输出结果：" + result.getResponse().getContentAsString());
    }

    /**
     * 测试商品类别保存
     * @throws Exception 异常对象
     */
    @Test
    public void testBackGoodsTypeSave() throws Exception{
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(1L);
        userInfo.setUserName("admin");

        Map<String,Object> map=new HashMap<>();
        map.put("gtname","军火11");
        map.put("gtremark","");

        Map<String,Object> plateMap=new HashMap<>();
        plateMap.put("gpid","1");
        map.put("goodsPlate",plateMap);

        String json=JSONObject.toJSONString(map);
        System.out.println(json);
        MvcResult result = mockMvc.perform(post("/admin/goodsTypeSave")
                .sessionAttr(Constract.ADMIN_LOGIN_FLAG, userInfo)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();// 返回执行请求的结果


        System.out.println("输出结果：" + result.getResponse().getContentAsString());
    }

    /**
     * 测试商品类别删除
     * @throws Exception 异常对象
     */
    @Test
    public void testBackGoodsTypeDel() throws Exception{
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(1L);
        userInfo.setUserName("admin");

        MvcResult result = mockMvc.perform(post("/admin/goodsTypeDel")
                .sessionAttr(Constract.ADMIN_LOGIN_FLAG, userInfo)
                .param("goodsTypeId","30")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();// 返回执行请求的结果


        System.out.println("输出结果：" + result.getResponse().getContentAsString());
    }

    /**
     * 测试读取新闻公告列表
     * @throws Exception 异常对象
     */
    @Test
    public void testShopNewsList() throws Exception{
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(1L);
        userInfo.setUserName("admin");

        MvcResult result = mockMvc.perform(get("/admin/shopNewsList")
                .sessionAttr(Constract.ADMIN_LOGIN_FLAG, userInfo)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();// 返回执行请求的结果


        System.out.println("输出结果：" + result.getResponse().getContentAsString());
    }

    /**
     测试保存新闻公告
     * @throws Exception 异常对象
     */
    @Test
    public void testShopNewsSave() throws Exception{
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(1L);
        userInfo.setUserName("admin");

        Map<String,Object> map=new HashMap<>();
        map.put("title","标题");
        map.put("context","<img src=\"/ytw/upload/attached/image/20171227/20171227110312_759.jpg\" alt=\"\" />");

        String json=JSONObject.toJSONString(map);
        System.out.println(json);
        MvcResult result = mockMvc.perform(post("/admin/shopNewsSave")
                .sessionAttr(Constract.ADMIN_LOGIN_FLAG, userInfo)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();// 返回执行请求的结果


        System.out.println("输出结果：" + result.getResponse().getContentAsString());
    }

    @Test
    public void testTest() throws Exception{
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(1L);
        userInfo.setUserName("admin");


        MvcResult result = mockMvc.perform(post("/admin/test")
                .sessionAttr(Constract.ADMIN_LOGIN_FLAG, userInfo)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();// 返回执行请求的结果


        System.out.println("输出结果：" + result.getResponse().getContentAsString());
    }
}