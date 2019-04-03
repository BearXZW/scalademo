package com.sana.bigdata.service;

import com.sana.bigdata.model.MyResult;
import com.sana.bigdata.model.User;

import java.util.List;

public interface UserService {

    int addUser(User user);

    List<User> findAllUser(int pageNum, int pageSize);

    User findUser(int id);

    void insert(User user);
    //登录方法
//    MyResult login(User user);

    User selectByUser(User user);

    User selectByName(String userName);

    //注册方法
    void register(User user);
}
