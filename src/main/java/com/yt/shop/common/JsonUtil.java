package com.yt.shop.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.HashMap;
import java.util.Map;

public class JsonUtil {

    /**
     * 转换对象到json字符串格式
     * @param code 状态码
     * @param message 回应消息描述
     * @param obj 回应对象
     * @return json字符串
     */
    public static String getReturnJson(int code,String message,Object obj)  {
        Map<String,Object> result=new HashMap<>();
        result.put("code",code);
        result.put("message",message);
        result.put("data",obj);
        return JSON.toJSONString(result);
    }

    public static String getReturnJson(int code,String message)  {
        Map<String,Object> result=new HashMap<>();
        result.put("code",code);
        result.put("message",message);
        return JSON.toJSONString(result);
    }
}
