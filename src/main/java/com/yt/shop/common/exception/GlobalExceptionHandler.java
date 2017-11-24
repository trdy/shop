package com.yt.shop.common.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * anthor:liyun
 * create:2017-11-23 12:36
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Map<String,Object> exceptionHeanler(){
        Map<String,Object> result=new HashMap<String,Object>();
        result.put("code",500);
        result.put("msg","系统出错，请联系管理员");
        return result;
    }
}
