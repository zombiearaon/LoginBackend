package com.example.demo.service.Implements;

import com.example.demo.entity.Token;
import com.example.demo.entity.User;
import com.example.demo.mapper.LoginMapper;
import com.example.demo.service.LoginService;
import com.example.demo.util.ApiResult;
import com.example.demo.util.TokenAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;

    @Override
    public ApiResult Login(User user) {
        String name = user.getUserName();
        String pwd = user.getPassword();
        User dbuser = loginMapper.Login(name);
        Boolean nameFlag = false;
        Boolean pwdFlag = false;
        if(dbuser != null){
            nameFlag = dbuser.getUserName().equals(name);
            pwdFlag = pwd.equals(dbuser.getPassword());
        }
        if(nameFlag){
            if(pwdFlag){
                Token token = new Token();
                token.setUserName(name);
                token.setToken(TokenAccess.genToken(name));
                return ApiResult.success(token);
            }
            return ApiResult.fail("密码不正确");
        }
        return ApiResult.fail("用户名不正确");
    }

    @Override
    public ApiResult regist(User user) {
        String name = user.getUserName();
        String pwd = user.getPassword();
        if(loginMapper.Login(name)!=null) {
            return ApiResult.fail("用户名已被占用");
        }else if(loginMapper.regist(name,pwd) != null){
            Token token = new Token();
            token.setUserName(name);
            token.setToken(TokenAccess.genToken(name));
            return  ApiResult.success("注册成功",token);
        }else{
            return ApiResult.fail("未知错误");
        }
    }
}
