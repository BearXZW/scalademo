package com.springclouddemo.demo.controller;


import com.springclouddemo.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Usercontroller {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/user/select/{id}")
    public User select(@PathVariable Integer id){
        return this.restTemplate.getForObject("http://localhost:8080/user/select/"+id,User.class);
    }

}
