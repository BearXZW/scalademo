package com.example.demo.mapper;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from user where id=#{id}")
    User selectByid(Integer id);

    @Insert("Insert into user(username,password,phone) value(#{username},#{password},#{phone})")
    int insert(User user);

}
