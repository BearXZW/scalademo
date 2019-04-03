package com.springbootdemo.demo.service.Impl;

import com.springbootdemo.demo.mapper.UserMapper;
import com.springbootdemo.demo.model.User;
import com.springbootdemo.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User select (Integer id){
        return userMapper.select(id);
    }
}
