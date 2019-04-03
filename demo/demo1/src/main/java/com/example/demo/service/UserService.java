package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {

    User selectByid(Integer id);

    int insert(User user);
}
