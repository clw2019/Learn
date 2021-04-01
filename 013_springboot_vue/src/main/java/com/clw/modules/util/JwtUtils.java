package com.clw.modules.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.clw.common.constant.MyConstant;

import java.util.Date;

/**
 * @Author: clw
 * @Description: JWT工具类
 * @Date: 2021/3/8 22:30
 */
public class JwtUtils {

    /***
    * Token 过期时间30min
    */
    // public static final long EXPIRE_TIME = 30 * 60 * 1000;
    // public static final String USER_NAME = "username";

    /**
    * @Author: clw
    * @Description: 生成签名token
    * @Param: [username, secret] [用户名, 秘钥]
    * @return: java.lang.String 加密的Token
    * @Date: 2021/3/8 22:56
    */
    public static String sign(String username, String secret) {
        Date date = new Date(System.currentTimeMillis() + MyConstant.JWT_EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create().withClaim(MyConstant.USERNAME, username).withExpiresAt(date).sign(algorithm);
    }

    /**
    * @Author: clw
    * @Description: 根据Token获取用户名
    * @Param: [token]
    * @return: java.lang.String
    * @Date: 2021/3/8 22:59
     * @param token
    */
    public static String getUsername(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim(MyConstant.USERNAME).asString();
    }
}
