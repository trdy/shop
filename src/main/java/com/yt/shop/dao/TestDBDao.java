package com.yt.shop.dao;

import com.yt.shop.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("testDBDao")
public class TestDBDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<UserInfo> findUserInfoList() {
        String sql="select * from tab_user";
        return jdbcTemplate.query(sql, new RowMapper<UserInfo>() {
            @Override
            public UserInfo mapRow(ResultSet rs, int i) throws SQLException {
                UserInfo u=new UserInfo();
                u.setUserId(rs.getInt("user_id"));
                u.setUserName(rs.getString("user_name"));
                u.setUserPass(rs.getString("user_pass"));
                u.setBirth(rs.getTimestamp("birth"));
                return u;
            }
        });
    }

    public List<UserInfo> findUserInfoList2(){
        Query query=entityManager.createQuery("from UserInfo u");
        
        return query.getResultList();
    }
}
