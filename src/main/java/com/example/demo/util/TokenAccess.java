package com.example.demo.util;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.UUID;

public class TokenAccess {
    @Value("${Token.ExpirationTime}")
    private static long ExpTime;

    @Value("${Token.Signature}")
    private static String Sign;


    public static String genToken(String name,String role){
        JwtBuilder builder = Jwts.builder();
        String Token = builder
                //Header加密算法和类型
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")
                //payload存储实际需要的数据
                .claim("username",name)
                .claim("role",role)
                .setSubject("Myapplication")
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
                .setId(UUID.randomUUID().toString())
                //signature定义签名信息
                .signWith(SignatureAlgorithm.HS256,"Myapplication")
                .compact();
        return Token;
    }

    public static boolean checkToken(String token){
        if(token == null){
            return false;
        }
        try {
            Jwts.parser().parseClaimsJws(token);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
