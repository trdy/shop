package com.yt.shop.common;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

public class JsonUtil {

    public static String getReturnJson(Object obj){
        Map<String,Object> result=new HashMap<>();
        result.put("code",obj);
        return JSON.toJSONString(result);
    }
}
