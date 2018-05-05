package com.tms.controller.user;


import ch.qos.logback.classic.Logger;
import com.tms.common.ApiResult;
import com.tms.common.ResponseCode;
import com.tms.entity.user.User;
import com.tms.service.user.IUserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/api/user")
public class UserController {

    private Logger logger = (Logger) LoggerFactory.getLogger(UserController.class);
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "addUser")
    @ResponseBody
    public ApiResult addUser(User user) {
        ApiResult<User> apiResult = userService.addUser(user);
        if(apiResult.isSuccuess()) {
            return ApiResult.createSuccess(null);
        }
        return ApiResult.createFail();
    }

}
