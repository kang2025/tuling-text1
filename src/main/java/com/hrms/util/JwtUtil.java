package com.hrms.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

/**
 * JWT工具类
 */
public class JwtUtil {

    // JWT签名所使用的密钥
    private static final String SECRET_KEY = "wwwdddd123"; // 替换为实际的密钥

    /**
     * 生成JWT令牌
     *
     * @param username 用户名，作为JWT的主体部分
     * @return 生成的JWT令牌字符串
     */
    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username) // 设置JWT的主体部分为用户名
                .setIssuedAt(new Date()) // 设置JWT的发行时间为当前时间
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 设置JWT的过期时间，此处为一天
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY) // 使用HS512算法和密钥对JWT进行签名
                .compact(); // 构建并返回JWT令牌
    }
}
