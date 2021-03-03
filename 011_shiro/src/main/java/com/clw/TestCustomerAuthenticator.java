package com.clw;

import com.clw.realm.CustomRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

/**
 * 测试自定义Realm
 */
public class TestCustomerAuthenticator {

    public static void main(String[] args) {

        // 1.创建SecurityManager
        DefaultSecurityManager securityManager = new DefaultSecurityManager();

        // 2.设置自定义Realm
        securityManager.setRealm(new CustomRealm());

        // 3.给全局安全工具类设置安全管理器
        SecurityUtils.setSecurityManager(securityManager);

        // 4.通过安全工具类获取Subject主体
        Subject subject = SecurityUtils.getSubject();

        // 5.创建令牌
        UsernamePasswordToken token = new UsernamePasswordToken("zs", "123");

        // 6.用户认证
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
    }
}
