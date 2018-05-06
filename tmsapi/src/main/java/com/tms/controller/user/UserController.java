package com.tms.controller.user;


import ch.qos.logback.classic.Logger;
import com.alibaba.fastjson.JSONObject;
import com.tms.common.ApiResult;
import com.tms.entity.user.User;
import com.tms.service.user.IUserService;
import com.tms.utils.PostToJSONUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
@RequestMapping(value = "/api/user")
public class UserController {

    private Logger logger = (Logger) LoggerFactory.getLogger(UserController.class);
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "addUser",method = RequestMethod.POST)
    @ResponseBody
    public ApiResult addUser(HttpServletRequest request) {
        logger.info("GO INTO Method");
        HashMap<String,Object> map = PostToJSONUtils.toJSON(request, HashMap.class);
        for(String key : map.keySet()) {
            logger.info("key:{},value:{}",key,map.get(key));
        }
        ApiResult<User> apiResult = userService.addUser(new User());
        if(apiResult.isSuccuess()) {
            return ApiResult.createSuccess(null);
        }
        return ApiResult.createFail();
    }

}
