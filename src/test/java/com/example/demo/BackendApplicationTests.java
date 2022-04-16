package com.example.demo;

import com.example.demo.controller.LoginController;
import com.example.demo.entity.User;
import com.example.demo.service.Implements.LoginServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackendApplicationTests {

    @Test
    void contextLoads() {
        String a = "test";
        System.out.println(methodtest(a));
        System.out.println(a);
    }

    String methodtest(String a){
        a = "NoTest";
        return a;
    }

    @Test
    void checkResponse(){

    }
}
