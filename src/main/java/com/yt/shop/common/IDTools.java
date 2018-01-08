package com.yt.shop.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.Calendar;
import java.util.Map;

public class IDTools {



    @Autowired
    private JdbcTemplate jdbcTemplate;

    public synchronized String genOrderNo() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        String sql = "select gen_val from id_table where gen_pk=1";
        Map<String, Object> map = jdbcTemplate.queryForMap(sql);
        Integer max = (Integer) map.get("gen_val") + 1;
        sql = "update id_table set gen_val=" + max + " where gen_pk=1";
        jdbcTemplate.execute(sql);
        return year + "" + max;
    }
}
