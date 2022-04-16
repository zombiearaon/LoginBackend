package com.example.demo.util;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.UUID;

public class TokenAccess {

    public static String genToken(String name){
        JwtBuilder builder = Jwts.builder();
        String Token = builder
                //Header加密算法和类型
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")
                //payload存储实际需要的数据
                .claim("username",name)
                .setSubject("Myapplication")
                //+后面的字符串是超时时间，这里设置30分钟
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
                .setId(UUID.randomUUID().toString())
                //signature定义签名信息
                .signWith(SignatureAlgorithm.HS256,"Myapplication")
                .compact();
        return Token;
    }

    public static boolean checkToken(String token,String name){
        if(StringUtil.NullOrEmpty(token)||StringUtil.NullOrEmpty(name)){
            return false;
        }
        try {
            Jws<Claims> myapplication = Jwts.parser().setSigningKey("Myapplication").parseClaimsJws(token);
            Claims claims = myapplication.getBody();
            if(!claims.get("username").equals(name)){
                return false;
            }
            System.out.println(myapplication);
        }catch (Exception e){
            return false;
        }
        return true;
    }

}
