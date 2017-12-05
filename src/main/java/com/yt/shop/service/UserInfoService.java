package com.yt.shop.service;

import com.yt.shop.dao.UserInfoDao;
import com.yt.shop.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userInfoService")
public class UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    public UserInfo findBankUserByNameAndPass(String userName, String userPass) {
        return userInfoDao.findBankUserByNameAndPass(userName,userPass);
    }
}
