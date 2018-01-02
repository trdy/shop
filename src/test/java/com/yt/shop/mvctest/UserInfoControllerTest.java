package com.yt.shop.mvctest;


import com.yt.shop.common.Constract;
import com.yt.shop.controller.UserInfoController;
import com.yt.shop.model.UserInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Base64Utils;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInfoControllerTest {

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
    UserInfoController userInfoController;

    @Test
    public void testValidUser() throws Exception {

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response =new MockHttpServletResponse();
        userInfoController.authImage(request,response);

        HttpSession session=request.getSession();
        String code= (String) session.getAttribute(Constract.VERIFY_CODE);

        Map<String,Object> map= new HashMap<>();
        map.put("userName","admin");
        map.put("userPass","1");
        map.put("checkCode",code);
        MvcResult result = mockMvc.perform(post("/admin/validUser")
                .sessionAttr(Constract.VERIFY_CODE,code)
                .contentType(MediaType.APPLICATION_JSON)
                .param("userName", "admin").param("userPass", "1").param("checkCode",code)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())// 模拟向testRest发送post请求
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果


        System.out.println("输出结果："+result.getResponse().getContentAsString());
    }

    @Test
    public void testBackUserInfoEdit() throws Exception{
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(1L);
        userInfo.setUserName("admin");


        MvcResult result = mockMvc.perform(get("/admin/userInfoEdit")
                .sessionAttr(Constract.ADMIN_LOGIN_FLAG, userInfo)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();// 返回执行请求的结果


        System.out.println("输出结果：" + result.getResponse().getContentAsString());
    }

    @Test
    public void testBackUserInfoSave() throws Exception{
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(1L);
        userInfo.setUserName("admin");

        FileInputStream fis = new FileInputStream("d:\\shoplogo.png");
        byte[] buf = new byte[fis.available()];
        fis.read(buf);
        String base64Code = Base64Utils.encodeToString(buf);

        MvcResult result = mockMvc.perform(post("/admin/userInfoSave")
                .sessionAttr(Constract.ADMIN_LOGIN_FLAG, userInfo)
                .param("email","eesen@126.com").param("question","1").param("answer","1")
                .param("headPic", "data:image/png;base64," + base64Code)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();// 返回执行请求的结果


        System.out.println("输出结果：" + result.getResponse().getContentAsString());
    }

    @Test
    public void testBackUserInfoUpdatePass() throws Exception{
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(1L);
        userInfo.setUserName("admin");

        MvcResult result = mockMvc.perform(post("/admin/userInfoUpdatePass")
                .sessionAttr(Constract.ADMIN_LOGIN_FLAG, userInfo)
                .param("oldPass","1").param("newPass","1").param("rePass","1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();// 返回执行请求的结果


        System.out.println("输出结果：" + result.getResponse().getContentAsString());
    }

    @Test
    public void testBackUserInfoExit() throws Exception{
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(1L);
        userInfo.setUserName("admin");

        MvcResult result = mockMvc.perform(get("/admin/userInfoExit")
                .sessionAttr(Constract.ADMIN_LOGIN_FLAG, userInfo)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();// 返回执行请求的结果


        System.out.println("输出结果：" + result.getResponse().getContentAsString());
    }
}
