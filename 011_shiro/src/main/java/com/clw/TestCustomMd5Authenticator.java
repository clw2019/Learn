package com.clw;

import com.clw.realm.CustomMd5Realm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

import java.util.Arrays;

public class TestCustomMd5Authenticator {

    public static void main(String[] args) {
        // 1.创建安全管理器
        DefaultSecurityManager securityManager = new DefaultSecurityManager();

        // 2.注入Realm
        // 2.1 创建Realm
        CustomMd5Realm realm = new CustomMd5Realm();
        // 2.2 设置realm使用hash凭证匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        // 加密算法 MD5
        credentialsMatcher.setHashAlgorithmName("md5");
        // 散列次数
        credentialsMatcher.setHashIterations(1024);
        realm.setCredentialsMatcher(credentialsMatcher);
        securityManager.setRealm(realm);

        // 3.把安全管理器放到安全管理工具类中
        SecurityUtils.setSecurityManager(securityManager);

        // 4.获取主体Subject
        Subject subject = SecurityUtils.getSubject();

        // 5.创建令牌
        UsernamePasswordToken token = new UsernamePasswordToken("zs", "123");

        // 6.认证
        try {
            System.out.println("认证之前认证状态：" + subject.isAuthenticated());
            subject.login(token);
            System.out.println("认证之前认证状态：" + subject.isAuthenticated());
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("认证失败：用户名不存在...");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("认证失败：密码错误...");
        }

        if (subject.isAuthenticated()) {
            // 基于角色的权限控制
            System.out.println(subject.hasRole("admin"));
            // 基于多角色权限控制
            System.out.println(subject.hasAllRoles(Arrays.asList("admin", "super")));
            // 是否具有其中一个角色
            boolean[] booleans = subject.hasRoles(Arrays.asList("admin", "super", "user"));
            for (boolean aBoolean : booleans) {
                System.out.println(aBoolean);
            }

            System.out.println("==================================================");

            // 基于权限访问字符串的访问控制   资源标识符:操作:资源类型
            System.out.println(subject.isPermitted("user:*:*"));

            // 分别具有哪些权限
            boolean[] permitted = subject.isPermitted("user:create:*", "user:update:*");
            for (boolean b : permitted) {
                System.out.println(b);
            }

            // 同时具有哪些权限
            boolean permittedAll = subject.isPermittedAll("user:create:*", "user:update:*");
            System.out.println(permittedAll);
        }
    }
}
