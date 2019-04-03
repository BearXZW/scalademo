package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/insert")
    public int insert(User user){
        User userdemo=new User();
        userdemo.setUsername("sana");
        userdemo.setPassword("123");
        userdemo.setPhone("12345");
        return userService.insert(userdemo);
    }

    @RequestMapping("/select")
    public User selectByid(Integer id){
        return userService.selectByid(1);
    }
}

