package com.sana.bigdata.controller;


import com.sana.bigdata.model.User;
import com.sana.bigdata.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@Api("swagger相关的api")
public class SwaggerController {
    @Autowired
    private UserService userService;

    private static  final Logger logger= LoggerFactory.getLogger(SwaggerController.class);

    @ApiOperation(value="根据id查询用户信息",notes="查询数据库中的用户")
    @ApiImplicitParam(name="id",value="用户id",paramType = "path",required = true,dataType = "Integer")
    @RequestMapping(value="{/id}",method = RequestMethod.GET)
    public User getUser(@PathVariable int id){
            logger.info("查询某个用户的信息");
            return userService.findUser(id);
    }
}
