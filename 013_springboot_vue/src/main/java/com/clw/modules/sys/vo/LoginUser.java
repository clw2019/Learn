package com.clw.modules.sys.vo;

import lombok.Data;

/**
 * @Author: clw
 * @Description: 登录用户信息
 * @Date: 2021/3/8 22:20
 */
@Data
public class LoginUser {

    /**
    * 登录用户id
    */
    private String id;

    /**
     * 登录用户姓名
     */
    private String username;

    /**
     * 登录用户密码
     */
    private String password;

    /**
    *  用户状态(1：正常 2：冻结 ）
    */
    private Integer status;
}
