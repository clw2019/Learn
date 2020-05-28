package com.clw.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/**
 * @Author: clw
 * @Description:
 * @Date: 2020/5/26 18:30
 */
public class TestShiro {
    // 测试Shiro， Hello World
    public static void main(String[] args) {
        // 创建"SecurityFactory",加载ini配置，并通过他创建SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        // shiro核心：SecurityManager
        SecurityManager securityManager = factory.getInstance();
        // 将SecurityManager托管到SecurityUtils工具类中(ops：之后可以不必关心SecurityManager)
        SecurityUtils.setSecurityManager(securityManager);
        // Subject作用：直接由用户使用，调用简单，其底层会调用SecurityManager的相关流程
        // Subject几乎可以调用shiro的所有功能(加密除外)
        Subject subject = SecurityUtils.getSubject();

        // 通过Subject获取当前用户的登录状态(ops：从session中同步信息)
        System.out.println("login before..." + subject.isAuthenticated());
        System.out.println("login before...登录凭证..." + subject.getPrincipal());
        //通过Subject进行身份认证
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123");
        subject.login(token);
        System.out.println("login after..." + subject.isAuthenticated());
        System.out.println("login after...登录凭证..." + subject.getPrincipal());

        // 角色校验
        boolean isAdmin = subject.hasRole("admin");
        System.out.println(isAdmin);

        // 权限校验
        System.out.println(subject.isPermitted("a:b"));
        System.out.println(subject.isPermitted("user:insert"));

        // 登出：省份信息，登录信息，权限信息，角色信息，会话信息，全部抹除
        subject.logout();
    }
}
