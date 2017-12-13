package com.yt.shop.controller;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class AdminIndexController {

    private Logger log= LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/admin/menu")
    @ResponseBody
    public String backIndexMenu(){
        Map<String,Object> result=new HashMap<>();
        result.put("menus","ss");
        return JSON.toJSONString(result);
    }
}
