package com.yt.shop.service;

import com.yt.shop.dao.TestDBJPA;
import com.yt.shop.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * anthor:liyun
 * create:2017-11-24 14:44
 */
@Service("testService")
public class TestService {

    @Autowired
    private TestDBJPA testDBJPA;



    @Transactional
    public UserInfo findUserInfoByNameAndPass(String userName, String userPass) {

        return testDBJPA.findUserInfoByNameAndPass(userName,userPass);
    }


}
