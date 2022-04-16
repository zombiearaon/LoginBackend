package com.example.demo.config;

import com.example.demo.util.ApiResult;
import com.example.demo.util.TokenAccess;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class BeforeControllerHandler implements HandlerInterceptor {
    /**
     * 响应前拦截器，定义再控制器运行之前校验token的逻辑
     * WebAppConfig中定义拦截器运行范围，这里的逻辑在不作用于LoginController被访问时
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        String userName = request.getHeader("userName");
        System.out.println("response:"+response);
        if(TokenAccess.checkToken(token,userName)){
            return true;
        }
        errorResponse(response,ApiResult.failToken("token失效"));
        return false;
    }

    /**
     *
     * @param response
     * @param apiResult
     * 定义Token响应异常的逻辑，将token失效信息转换为json
     */
    public void errorResponse(HttpServletResponse response,ApiResult apiResult){
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            ObjectMapper objectMapper = new ObjectMapper();
            out.println(objectMapper.writeValueAsString(apiResult));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.flush();
        out.close();
    }
}
