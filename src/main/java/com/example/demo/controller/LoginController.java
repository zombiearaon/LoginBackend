package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.LoginService;
import com.example.demo.util.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class LoginController {
    @Autowired
    private LoginService loginservice;

    @PostMapping("login")
    public ApiResult login(@RequestBody User user){
        return loginservice.Login(user);
    }

    @PostMapping("regist")
    public ApiResult regist(User user){
        return loginservice.regist(user);
    }
}
