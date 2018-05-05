package com.tms.dao.user;

import com.tms.entity.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUserMapper {
    /**
     * 添加用户信息
     * @param user 用户信息
     * @return 成功记录数
     */
    Integer addUser(@Param("user") User user);

    /**
     * 根据用户id删除用户 -> 作废（注销）
     * @param userId
     * @return
     */
    Integer delUser(String userId);

    /**
     * 更新用户信息
     * @param user 待更新用户信息
     * @return 更新成功条数
     */
    Integer updateUser(User user);

    /**
     * 查询所有用户信息
     * @return 所有用户信息
     */
    List<User> findUser();

    /**
     * 根据用户id查询用户信息
     * @param userId 用户ID
     * @return 具体的用户信息
     */
    User findUserByUserId(String userId);

    /**
     * 根据用户昵称模糊查询
     * @param userName 用户昵称
     * @return 所有符合的用户信息
     */
    List<User> findUserByUserName(String userName);
}
