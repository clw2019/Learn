package com.clw.modules.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Author: clw
 * @Description:
 * @Date: 2021/3/14 0:20
 */
public class JwtToken implements AuthenticationToken {
    private String token;

    public JwtToken(String token) {
        this.token = token;
    }
    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
