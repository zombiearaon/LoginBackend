package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.util.ApiResult;

public interface LoginService {
    ApiResult Login(User user);
}
