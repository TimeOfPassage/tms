package com.tms.service.user;

import com.tms.common.ApiResult;
import com.tms.entity.user.User;

public interface IUserService {

    ApiResult<User> addUser(User user);

}
