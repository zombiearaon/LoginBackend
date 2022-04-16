package com.example.demo.util;

public class StringUtil {
    public static boolean NullOrEmpty(String val){
        if(val == null || val.equals("")){
            return true;
        }
        return false;
    }
}
