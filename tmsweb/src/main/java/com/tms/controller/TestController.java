package com.tms.controller;

import com.alibaba.fastjson.JSONObject;
import com.tms.utils.HttpUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class TestController {

    @RequestMapping(value = "toTest")
    public ModelAndView toTest() {
        ModelAndView mv = new ModelAndView();

        CloseableHttpClient httpClient = HttpClients.createDefault();
        //HttpGet httpGet = new HttpGet("http://127.0.0.1:8080/tmsapi/api/category/list");
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("userName","heyang");
        map.put("password","heyang");
        map.put("salt","123");
        map.put("email","123@qq.com");
        map.put("age","11");
        map.put("mobile","13236571319");
        map.put("intro","ddddd");
        JSONObject jObj = JSONObject.parseObject(HttpUtils.doHttp(map,"http://127.0.0.1:8080/tmsapi/api/user/addUser"));;
        System.out.println("============");
        System.out.println(jObj);
        mv.setViewName("test");
        mv.addObject("message", "Hello World!!!!!");
        mv.addObject("obj",jObj);
        return mv;
    }
}
