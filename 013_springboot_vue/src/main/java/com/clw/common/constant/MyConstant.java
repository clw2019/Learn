package com.clw.common.constant;

/**
 * @Author: clw
 * @Description: 通用常量
 * @Date: 2021/3/13 23:16
 */
public interface MyConstant {
    /**
     * 成功返回值
     */
    Integer SUCCESS_CODE = 200;
    /**
     * 失败返回值
     */
    Integer FAIL_CODE = 400;
    /**
     * 未删除
     */
    Integer DEL_FLAG_0 = 0;
    /**
     * 已删除
     */
    Integer DEL_FLAG_1 = 1;



    /**
     * 密码加密次数
     */
    Integer ITERATOR = 10000;

    String SUCCESS_MESSAGE = "success";
    String FAIL_MESSAGE = "fail";

    /**
     * 登录成功
     */
    String LOGIN_SUCCESS = "登录成功";
    /**
     * 登录失败
     */
    String LOGIN_FAIL = "登录失败";

    String USER_COUNT_NOT_FOUND = "用户不存在";
    String USER_CANCELED = "用户已注销";
    String PASSWORD_ERROR = "密码错误";
    String USER_COUNT_BLOCKED = "账号已被锁定";
    String TOKEN_EXPIRED = "token过期";
    String TOKEN_ERROR = "token错误，请重新登入";

    String USERNAME_KEY = "username";
    // 用户登录传入的密码加密后
    String PASSWORD_ENCRYPTION_KEY = "password";

    /*JWT相关常量*/

    /**
     * token 过期时间
     */
    Long JWT_EXPIRE_TIME = Long.valueOf(30 * 60 * 1000);

    /**
     * jwt 秘钥
     */
    String JWT_SECRET = "secret";

    /**
     * Token的key
     */
    String TOKEN_KEY = "Authorization";

    String USERNAME = "username";
}
