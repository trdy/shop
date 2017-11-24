package com.yt.shop.service;

import com.yt.shop.dao.TestDBDao;
import com.yt.shop.dao.TestMapper;
import com.yt.shop.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.ws.ServiceMode;
import java.util.List;
import java.util.Map;

/**
 * anthor:liyun
 * create:2017-11-24 14:44
 */
@Service("testService")
public class TestService {

    @Autowired
    private TestDBDao testDBDao;

    @Autowired
    private TestMapper testMapper;

    @Transactional
    public UserInfo findUserInfoByNameAndPass(String userName, String userPass) {
        return testDBDao.findUserInfoByNameAndPass(userName,userPass);
    }

    @Transactional
    public List<Map<String,Object>> findUserInfoList(){
        return testMapper.findUserInfoList();
    }
}
