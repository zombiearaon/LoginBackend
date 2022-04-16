package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootConfiguration
public class WebAppConfig implements WebMvcConfigurer {
    @Autowired
    BeforeControllerHandler beforeControllerHandler;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(beforeControllerHandler).addPathPatterns("/**").excludePathPatterns("/auth/**");
    }
}
