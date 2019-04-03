package com.springbootdemo.demo.mapper;

import com.springbootdemo.demo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select(value="select * from user where id=#{id}")
    public User select(Integer id);
}
