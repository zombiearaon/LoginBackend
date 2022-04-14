package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface LoginMapper {
    @Select("SELECT `user_name`, `password` FROM `user` WHERE user_name = #{username}")
    User Login(@Param("username") String name);

    Integer regist(@Param("user_name")String username,@Param("password")String password);
}
