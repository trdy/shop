package com.yt.shop.dao;

import com.yt.shop.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("testDBDao")
public class TestDBDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String,Object>> findUserInfoList() {
        String sql="select * from tab_user";
        RowMapper<UserInfo> rowMapper = new BeanPropertyRowMapper<UserInfo>(UserInfo.class);
        return jdbcTemplate.queryForList(sql,rowMapper);
    }
}
