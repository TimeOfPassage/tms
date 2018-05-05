package com.tms.service.user.impl;

import com.tms.common.ApiResult;
import com.tms.dao.user.IUserMapper;
import com.tms.entity.user.User;
import com.tms.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private IUserMapper userMapper;

    @Override
    @Transactional
    public ApiResult<User> addUser(User user) {
        Integer isAddSucc = 0;
        try {
            isAddSucc = userMapper.addUser(user);
            if(isAddSucc > 0 ){
                return ApiResult.createSuccess(null);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ApiResult.createFail();
    }
}
