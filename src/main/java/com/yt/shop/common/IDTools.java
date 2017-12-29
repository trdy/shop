package com.yt.shop.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class IDTools {

    @Autowired
    JdbcTemplate jdbcTemplate;


}
