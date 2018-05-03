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

@Controller
public class TestController {

    @RequestMapping(value = "toTest")
    public ModelAndView toTest() {
        ModelAndView mv = new ModelAndView();

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://127.0.0.1:8080/tmsapi/api/category/list");
        JSONObject jObj = JSONObject.parseObject(HttpUtils.doHttp(null,"http://127.0.0.1:8080/tmsapi/api/category/list"));;
        mv.setViewName("test");
        mv.addObject("message", "Hello World!!!!!");
        mv.addObject("obj",jObj);
        return mv;
    }
}
