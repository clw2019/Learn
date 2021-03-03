package com.clw.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * 自定义Realm，将认证/授权的数据来源转为数据库
 */
public class CustomRealm extends AuthorizingRealm {
    // 授权
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    // 认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 从token中获取用户名
        String username = (String) token.getPrincipal();
        System.out.println(username);
        // 根据用户名查询数据库
        if ("zs".equals(username)) {
            /**
             * Object principal : 数据库中的用户名
             * Object credentials : 数据库中的密码
             * String realmName : 当前realm的名字，this.getName()
             */
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, "123", this.getName());
            return simpleAuthenticationInfo;
        }
        return null;
    }
}
