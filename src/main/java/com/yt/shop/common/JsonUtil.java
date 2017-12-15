package com.yt.shop.common;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

public class JsonUtil {

    /**
     * 转换对象到json字符串格式
     * @param obj 待转换对象
     * @return json字符串
     */
    public static String getReturnJson(Object obj){
        Map<String,Object> result=new HashMap<>();
        result.put("code",obj);
        return JSON.toJSONString(result);
    }
}
