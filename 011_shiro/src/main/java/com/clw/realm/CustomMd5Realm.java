package com.clw.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class CustomMd5Realm extends AuthorizingRealm {
    // 授权
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("=================授权=================");
        String primaryPrincipal = (String) principals.getPrimaryPrincipal();
        System.out.println("身份信息：" + primaryPrincipal);

        // 根据身份信息 -- 用户名 获取当前用户的角色信息，以及权限信息
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 将数据库中查询出来的角色信息赋给对象
        simpleAuthorizationInfo.addRole("admin");
        simpleAuthorizationInfo.addRole("user");

        // 将数据库中查询权限信息赋值给权限对象
        simpleAuthorizationInfo.addStringPermission("user:*:*");

        return simpleAuthorizationInfo;
    }

    // 认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 获取身份信息
        String principal = (String) token.getPrincipal();
        System.out.println(principal);
        // 根据用户名查询数据库
        if ("zs".equals(principal)) {
            // "123" MD5 加密后 "202cb962ac59075b964b07152d234b70"
            //return new SimpleAuthenticationInfo(principal, "202cb962ac59075b964b07152d234b70", this.getName());
            // "123" MD5 + salt("x0*7ps") 加密后 "c15be9a15a0a238084e0c5a846f3a7b4"
            // "123" MD5 + salt("x0*7ps") + 散列次数(1024) 加密后 "c15be9a15a0a238084e0c5a846f3a7b4"
            //ByteSource salt = ByteSource.Util.bytes("x0*7ps");
            //return new SimpleAuthenticationInfo(principal, "c15be9a15a0a238084e0c5a846f3a7b4", salt, this.getName());
            // "123" MD5 + salt("x0*7ps") + 散列次数(1024) 加密后 "44c42bc682c33a4dae2af47eba4c8011"
            ByteSource salt = ByteSource.Util.bytes("x0*7ps");
            return new SimpleAuthenticationInfo(principal, "44c42bc682c33a4dae2af47eba4c8011", salt, this.getName());
        }
        return null;
    }
}
