package com.clw.util;

import com.clw.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.Map;

@Slf4j
public class JwtTokenUtil {

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
        //return Jwts.parser()
        //        .setSigningKey(secret)
        //        .parseClaimsJws(token)
        //        .getBody();
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    //.setSigningKey(secret)
                    .setSigningKey("secret")
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.info("JWT格式验证失败:{}",token);
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

    public static String getUserNameFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        String username = (String) claims.get("username");
        System.out.println("JWTUtils... getUserNameFromToken " + username);
        return username;
    }

    /**
     * token 是否过期
     * @param token
     * @param user
     * @return
     */
    public static boolean validateToken(String token, User user) {
        String username = getUserNameFromToken(token);
        return username.equals(user.getUsername()) && !isTokenExpired(token);
    }
    //private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);
    //private static final String CLAIM_KEY_USERNAME = "sub";
    //private static final String CLAIM_KEY_CREATED = "created";
    //@Value("${jwt.secret}")
    //private String secret;
    //@Value("${jwt.expiration}")
    //private Long expiration;
    ////@Value("${jwt.tokenHead}")
    ////private String tokenHead;
    //
    ///**
    // * 根据负责生成JWT的token
    // */
    //public String generateToken(Map<String, Object> claims) {
    //    return Jwts.builder()
    //            .setClaims(claims)
    //            .setExpiration(generateExpirationDate())
    //            .signWith(SignatureAlgorithm.HS256, secret)
    //            .compact();
    //}
    //
    ///**
    // * 从token中获取JWT中的负载
    // */
    //private Claims getClaimsFromToken(String token) {
    //    Claims claims = null;
    //    try {
    //        claims = Jwts.parser()
    //                .setSigningKey(secret)
    //                .parseClaimsJws(token)
    //                .getBody();
    //    } catch (Exception e) {
    //        LOGGER.info("JWT格式验证失败:{}", token);
    //    }
    //    return claims;
    //}
    //
    ///**
    // * 生成token的过期时间
    // */
    //private Date generateExpirationDate() {
    //    return new Date(System.currentTimeMillis() + expiration * 1000);
    //}
    //
    ///**
    // * 从token中获取登录用户名
    // */
    //public String getUserNameFromToken(String token) {
    //    String username;
    //    try {
    //        Claims claims = getClaimsFromToken(token);
    //        username = claims.getSubject();
    //    } catch (Exception e) {
    //        username = null;
    //    }
    //    return username;
    //}
    //
    ///**
    // * 验证token是否还有效,且正确
    // *
    // * @param token       客户端传入的token
    // *  从数据库中查询出来的用户信息
    // */
    //public boolean validateToken(String token, User user) {
    //    String username = getUserNameFromToken(token);
    //    return username.equals(user.getUsername()) && !isTokenExpired(token);
    //}
    //
    ///**
    // * 判断token是否已经失效
    // */
    //private boolean isTokenExpired(String token) {
    //    Date expiredDate = getExpiredDateFromToken(token);
    //    return expiredDate.before(new Date());
    //}
    //
    ///**
    // * 从token中获取过期时间
    // */
    //private Date getExpiredDateFromToken(String token) {
    //    Claims claims = getClaimsFromToken(token);
    //    return claims.getExpiration();
    //}
    //
    /////**
    //// * 根据用户信息生成token
    //// */
    ////public String generateToken(UserDetails userDetails) {
    ////    Map<String, Object> claims = new HashMap<>();
    ////    claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
    ////    claims.put(CLAIM_KEY_CREATED, new Date());
    ////    return generateToken(claims);
    ////}
    //
    ///**
    // * 当原来的token没过期时是可以刷新的
    // *
    // * @param oldToken 带tokenHead的token
    // */
    ////public String refreshHeadToken(String oldToken) {
    ////    if(StrUtil.isEmpty(oldToken)){
    ////        return null;
    ////    }
    ////    String token = oldToken.substring(tokenHead.length());
    ////    if(StrUtil.isEmpty(token)){
    ////        return null;
    ////    }
    ////    //token校验不通过
    ////    Claims claims = getClaimsFromToken(token);
    ////    if(claims==null){
    ////        return null;
    ////    }
    ////    //如果token已经过期，不支持刷新
    ////    if(isTokenExpired(token)){
    ////        return null;
    ////    }
    ////    //如果token在30分钟之内刚刷新过，返回原token
    ////    if(tokenRefreshJustBefore(token,30*60)){
    ////        return token;
    ////    }else{
    ////        claims.put(CLAIM_KEY_CREATED, new Date());
    ////        return generateToken(claims);
    ////    }
    ////}
    //
    ///**
    // * 判断token在指定时间内是否刚刚刷新过
    // * @param token 原token
    // * @param time 指定时间（秒）
    // */
    ////private boolean tokenRefreshJustBefore(String token, int time) {
    ////    Claims claims = getClaimsFromToken(token);
    ////    Date created = claims.get(CLAIM_KEY_CREATED, Date.class);
    ////    Date refreshDate = new Date();
    ////    //刷新时间在创建时间的指定时间内
    ////    if(refreshDate.after(created)&&refreshDate.before(DateUtil.offsetSecond(created,time))){
    ////        return true;
    ////    }
    ////    return false;
    ////}
}
