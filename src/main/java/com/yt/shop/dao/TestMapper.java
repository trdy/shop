package com.yt.shop.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * anthor:liyun
 * create:2017-11-24 16:26
 */
@Repository("testMapper")
public class TestMapper {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<Map<String,Object>> findUserInfoList(){
        String sql="select * from tab_user";
        return this.jdbcTemplate.queryForList(sql);
    }

}
