package com.yt.shop.mvctest;


import com.alibaba.fastjson.JSONObject;
import com.yt.shop.common.Constract;
import com.yt.shop.controller.UserInfoController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

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
        map.put("checkcode",code);
        MvcResult result = mockMvc.perform(post("/admin/validUser")
                .contentType(MediaType.APPLICATION_JSON)
                .param("userName", "admin").param("userPass", "1").param("checkcode",code)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())// 模拟向testRest发送post请求
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果


        System.out.println("输出结果："+result.getResponse().getContentAsString());
    }
}
