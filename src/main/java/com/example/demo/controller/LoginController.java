package com.example.demo.controller;


import com.example.demo.entity.LoginUser;
import com.example.demo.service.loginService;
import com.example.demo.util.TokenAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class LoginController {

    @PostMapping("login")
    public LoginUser login(LoginUser user){
        //查询用户信息


        String name = user.getUserName();
        String pwd = user.getPassword();

        // TODO: 2022/4/10 验证密码是否正确 
        if(name.equals("")){
            user.setToken(TokenAccess.genToken(name,pwd));
            return user;
        }
        return null;
    }

    @PostMapping("regist")
    public LoginUser regist(){
        return null;
    }

    @PostMapping("checkToken")
    public Boolean checkToken(LoginUser user){
        String token = user.getToken();
        return TokenAccess.checkToken(token);
    }
}
