package com.example.demo.controller;

import com.example.demo.entity.Token;
import com.example.demo.entity.User;
import com.example.demo.service.LoginService;
import com.example.demo.util.ApiResult;
import com.example.demo.util.TokenAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class LoginController {
    @Autowired
    private LoginService loginservice;


    @GetMapping("login")
    public ApiResult login(User user){
      return loginservice.Login(user);
    }

    @PostMapping("regist")
    public ApiResult regist(){
        return null;
    }

    @PostMapping("checkToken")
    public Boolean checkToken(Token user){
        String token = user.getToken();
        return TokenAccess.checkToken(token);
    }
}
