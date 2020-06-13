package com.clw.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.Map;

/**
 * @Author: clw
 * @Description:
 * @Date: 2020/6/6 16:51
 */
public class JWTUtil {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 根据自定义的负载生成Token
     * @param claims
     * @return
     */
    public static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)  //自定义内容
                //.setIssuedAt(new Date())    //设置Token签发时间
                .setExpiration(new Date(System.currentTimeMillis() + 604800 * 1000))    //设置Token过期时间
                .signWith(SignatureAlgorithm.HS256, "secret")     //设置签名 使用HS512算法，并设置SecretKey(字符串)
                .compact();
    }

    /**
     * 从Token中获取JWT负载
     * @param token
     * @return
     */
    public static Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey("secret")
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            System.out.println("JWT格式验证失败:{}" + token);
        }
        return claims;
    }

    /**
     * 判断token是否过期
     * @param token
     * @return
     */
    public static boolean isTokenExpired(String token) {
        Claims claims = getClaimsFromToken(token);
        Date expirationDate = claims.getExpiration();
        return expirationDate.before(new Date());
    }
}
