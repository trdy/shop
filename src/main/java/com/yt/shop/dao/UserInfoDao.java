package com.yt.shop.dao;

import com.yt.shop.model.UserInfo;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository("userInfoDao")
public class UserInfoDao {

    private org.slf4j.Logger log= LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UserInfo findBackUserByNameAndPass(String userName, String userPass) {
        UserInfo userInfo=null;
        String hql="from UserInfo u where u.userState=1 and u.userType.userTypeId<100 and u.userName=? and u.userPass=?";
        Query query=entityManager.createQuery(hql);
        query.setParameter(1,userName);
        query.setParameter(2,userPass);
        try {
            userInfo = (UserInfo) query.getSingleResult();
        }catch (Exception e){
            log.error("验证用户名和密码到数据库访问过程中"+e);
        }
        return userInfo;
    }
}
