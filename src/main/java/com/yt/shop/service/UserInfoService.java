package com.yt.shop.service;

import com.yt.shop.dao.UserInfoDao;
import com.yt.shop.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userInfoService")
public class UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Transactional
    public UserInfo findBackUserByNameAndPass(String userName, String userPass) {
        return userInfoDao.findBackUserByNameAndPass(userName,userPass);
    }
}
