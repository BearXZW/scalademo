package com.springbootdemo.demo.controller;

        import com.springbootdemo.demo.model.User;
        import com.springbootdemo.demo.service.UserService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;



    @RequestMapping("/select/{id}")
    public User select(@PathVariable Integer id){
        return userService.select(id);
    }

}
