package com.yt.shop.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

/**
 * anthor:liyun
 * create:2017-11-23 12:36
 */
@RestControllerAdvice
//@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 系统全局异常
     * @return json字符串 {"code",500}
     */
    /*@ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Map<String,Object> exceptionHeanler(){
        Map<String,Object> result=new HashMap<String,Object>();
        result.put("code",500);
        result.put("msg","系统出错，请联系管理员");
        return result;
    }*/

    /**
     * 违反主键约束异常
     * @param ex 异常对象
     * @return 出错状态码和异常信息
     */
    @ExceptionHandler(value = { ConstraintViolationException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse constraintViolationException(ConstraintViolationException ex) {
        return new ApiErrorResponse(500, 5001, ex.getMessage());
    }

    /**
     * 参数传递异常
     * @param ex 异常对象
     * @return 出错状态码和异常信息
     */
    @ExceptionHandler(value = { IllegalArgumentException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse IllegalArgumentException(IllegalArgumentException ex) {
        return new ApiErrorResponse(501, 5002, ex.getMessage());
    }

    /**
     * 没有找到正确的请求异常
     * @param ex 异常对象
     * @return 出错状态码和异常信息
     */
    @ExceptionHandler(value = { NoHandlerFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse noHandlerFoundException(Exception ex) {
        return new ApiErrorResponse(404, 4041, ex.getMessage());
    }

    /**
     * 返回错误状态
     * @param ex 异常对象
     * @return 出错状态码和异常信息
     */
    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse unknownException(Exception ex) {
        return new ApiErrorResponse(500, 5002, ex.getMessage());
    }
}
