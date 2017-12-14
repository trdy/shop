package com.yt.shop.service;

import com.yt.shop.dao.UserInfoJpa;
import com.yt.shop.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userInfoService")
public class UserInfoService {


    @Autowired
    private UserInfoJpa userInfoJpa;

    /**
     * 后台管理员登录根据用户名和密码查询后台用户
     * @param userName
     * @param userPass
     * @return
     */
    @Transactional
    public UserInfo findBackUserByNameAndPass(String userName, String userPass) {
        return userInfoJpa.findBackUserByNameAndPass(userName,userPass);
    }

    /**
     * 后台管理员登录后，访问首页加载用户信息
     * @param userId
     * @return
     */
    @Transactional
    public UserInfo findUserInfoById(Long userId) {
        return userInfoJpa.findOne(userId);
    }
}
