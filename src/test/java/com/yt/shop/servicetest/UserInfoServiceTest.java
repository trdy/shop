package com.yt.shop.servicetest;

import com.yt.shop.common.MD5;
import com.yt.shop.model.UserInfo;
import com.yt.shop.service.UserInfoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInfoServiceTest {


    @Autowired
    private WebApplicationContext wac; // 注入WebApplicationContext

    @Autowired
    private UserInfoService userInfoService;

    @Test
    public void testFindBackUserByNameAndPass(){
        UserInfo userInfo=userInfoService.findBackUserByNameAndPass("admin", MD5.GetMD5Code("1"));
        Assert.assertTrue("正确",null!=userInfo);
        System.out.println(userInfo);
    }
}
