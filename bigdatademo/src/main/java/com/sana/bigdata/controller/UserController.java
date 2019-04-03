package com.sana.bigdata.controller;


import com.alibaba.fastjson.JSONObject;
import com.sana.bigdata.BigdataApplication;
import com.sana.bigdata.annotation.UserLoginToken;
import com.sana.bigdata.model.MyResult;
import com.sana.bigdata.model.User;
import com.sana.bigdata.service.TokenService;
import com.sana.bigdata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.naming.event.ObjectChangeListener;
import java.util.logging.Logger;

@RestController
@CrossOrigin
@RequestMapping(value="/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    //注册token服务
    private TokenService tokenService;
    @PostMapping("/login")
    public Object login(@RequestBody User user){
        JSONObject jsonObject=new JSONObject();
        User userForBase=userService.selectByUser(user);
        if(userForBase==null){
            jsonObject.put("message","登录失败，用户不存在");
            return jsonObject;
        }
        else{
            if(!userForBase.getPassword().equals(user.getPassword())){
                jsonObject.put("message","登录失败，密码错误");
                return jsonObject;
            }
            else{
                String token = tokenService.getToken(userForBase);
                jsonObject.put("token", token);
                jsonObject.put("user", userForBase);
                return jsonObject;
            }
        }
    }
    @UserLoginToken
    @GetMapping("/getMessage")
    public  String getMessage(){
        return "你已经通过验证";
    }

    //注册功能
    @ResponseBody
    @PostMapping("/register")
    public  Object register(User user){
        JSONObject jsonObject=new JSONObject();
        User userForBase=userService.selectByUser(user);
        if(userForBase==null){
            userService.insert(user);
            jsonObject.put("message","注册成功");
        }else{
            jsonObject.put("message","注册失败，用户名已存在");
        }
        return jsonObject;
    }
//    @RequestMapping("/index")
//    public String index(Model model, User user) {
//
//        return "index";
//    }

    @ResponseBody
    @RequestMapping(value="/test")
    public Object showUser(User user){
        return userService.findUser(1);
    }
    @ResponseBody
    @RequestMapping(value="/add",produces = {"application/json;charset=UTF-8"})
    public int addUser(User user){

        return userService.addUser(user);
    }

    @ResponseBody
    @RequestMapping(value="/all/{pageNum}/{pageSize}",produces = {"application/json;charset=UTF-8"})
    public Object findAllUser(@PathVariable("pageNum")int pageNum,@PathVariable("pageSize") int pageSize){
        return userService.findAllUser(pageNum,pageSize);
    }
}
