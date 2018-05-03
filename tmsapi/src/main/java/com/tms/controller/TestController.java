package com.tms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/api/category")
public class TestController {

    @RequestMapping(value = "list")
    @ResponseBody
    public Map<String, Object> listCategroy() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "wangwu");
        map.put("age", 20);
        return map;
    }
}
