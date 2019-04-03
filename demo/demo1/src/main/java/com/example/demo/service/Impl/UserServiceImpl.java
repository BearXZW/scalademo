package com.example.demo.service.Impl;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int insert(User user){
        return userMapper.insert(user);
    }
    @Override
    public User selectByid(Integer id){
        return userMapper.selectByid(id);
    }

}
