package com.example.demo.util;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ApiResult {
    //返回的状态码
    private int code;
    //信息
    private String msg;
    //返回的实际数据
    @JsonInclude
    private Object data;



    /**
     * 构造方法
     */
    public ApiResult(int code) {
        this.code = code;
    }

    public ApiResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ApiResult(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public ApiResult(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    //getter和setter
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }


    /****
     * 正确响应的返回方法
     */
    public static ApiResult success(){
        return new ApiResult(0);
    }

    public static ApiResult success(Object data){
        return new ApiResult(0,data);
    }

    public static ApiResult success(String message){
        return new ApiResult(0,message);
    }

    public static ApiResult success(String message,Object data){
        return new ApiResult(0,message,data);
    }

    /**
     * 参数获取失败响应的返回方法
     */
    public static ApiResult fail(){
        return new ApiResult(-1);
    }

    public static ApiResult fail(Object data){
        return new ApiResult(-1,data);
    }

    public static ApiResult fail(String message){
        return new ApiResult(-1,message);
    }

    /**
     * token解析失败响应的返回方法
     */
    public static ApiResult failToken(String message){
        return new ApiResult(-10,message);
    }
}
