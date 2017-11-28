package com.yt.shop.controller;

import com.yt.shop.model.Person;
import com.yt.shop.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DemoController {

    private Logger log= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DemoService demoService;


    @RequestMapping("/person/savePerson")
    public int savePerson(@RequestBody Person person){
        log.info("测试保存person的方法");
        try {
            Person p = demoService.insertPerson(person);
            log.debug("保存person对象成功");
            return 1;
        }catch (Exception e){
            log.error("保存person对象出错："+e.toString());
            return 0;
        }

    }

    @RequestMapping("/person/listPerson")
    public List<Person> listPerson(){
        log.info("测试查询person集合的方法");
        try {
            List<Person> list = demoService.findPersonList();
            log.debug("查询person集合成功");
            return list;
        }catch (Exception e){
            log.error("查询person集合出错:"+e.getMessage());
            return new ArrayList<Person>();
        }

    }




}
