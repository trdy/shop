package com.yt.shop.dao;

import com.yt.shop.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository("userInfoDao")
public class UserInfoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UserInfo findBankUserByNameAndPass(String userName, String userPass) {
        String hql="from UserInfo u where u.userState=1 and u.userType.userTypeId<100 and u.userName=? and u.userPass=?";
        Query query=entityManager.createQuery(hql);
        UserInfo userInfo= (UserInfo) query.getSingleResult();
        return userInfo;
    }
}
