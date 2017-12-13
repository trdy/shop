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
     * 根据用户名和密码查询后台用户
     * @param userName
     * @param userPass
     * @return
     */
    @Transactional
    public UserInfo findBackUserByNameAndPass(String userName, String userPass) {
        return userInfoJpa.findBackUserByNameAndPass(userName,userPass);
    }
}
