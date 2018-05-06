package com.tms.controller;

import com.alibaba.fastjson.JSONObject;
import com.tms.utils.HttpUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TestController {

    @RequestMapping(value = "toTest")
    public ModelAndView toTest() {
        ModelAndView mv = new ModelAndView();

        CloseableHttpClient httpClient = HttpClients.createDefault();
        //HttpGet httpGet = new HttpGet("http://127.0.0.1:8080/tmsapi/api/category/list");
        JSONObject json = new JSONObject();
        json.put("userName","heyang");
        json.put("password","heyang");
        json.put("salt","123");
        json.put("email","123@qq.com");
        json.put("age","11");
        json.put("mobile","13236571319");
        json.put("intro","ddddd");
        JSONObject jObj = JSONObject.parseObject(HttpUtils.doHttpPost(json,"http://127.0.0.1:8080/tmsapi/api/user/addUser"));;
        System.out.println("============");
        System.out.println(jObj);
        mv.setViewName("test");
        mv.addObject("message", "Hello World!!!!!");
        mv.addObject("obj",jObj);
        return mv;
    }
}
