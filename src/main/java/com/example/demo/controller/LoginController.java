package com.example.demo.controller;

import com.example.demo.entity.Token;
import com.example.demo.entity.User;
import com.example.demo.service.LoginService;
import com.example.demo.util.ApiResult;
import com.example.demo.util.TokenAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class LoginController {
    @Autowired
    private LoginService loginservice;


    @GetMapping("login")
    public ApiResult login(@RequestParam String username, @RequestParam String password){
        User user = new User(username,password);
        return loginservice.Login(user);
    }

    @GetMapping("regist")
    public ApiResult regist(@RequestParam String username, @RequestParam String password){
        User user = new User(username,password);
        return loginservice.regist(user);
    }
}
