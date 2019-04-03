package com.sana.bigdata.mapper;

import com.sana.bigdata.model.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    //添加列举所有的
    List<User> selectAllUser();
    //实现登录方法
    List<User> login(User user);

    //通过username查找
    User selectByUser(User user);

    //通过username查找
    User selectByName(String userName);

    //实现注册方法
    void register(User user);
}