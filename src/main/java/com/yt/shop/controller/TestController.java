package com.yt.shop.controller;

import com.yt.shop.common.IDTools;
import com.yt.shop.common.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @Autowired
    private IDTools idTools;

    @RequestMapping(value = "/test/test")
    @ResponseBody
    public String test(){
        return JsonUtil.getReturnJson(1,"ok",idTools.genOrderNo());
    }

}
