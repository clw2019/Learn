package com.clw.config;

import com.clw.shiro.CustomRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 *  用来整合 shiro 框架相关的配置类
 */
@Configuration
public class ShiroConfig {

    @Value("${md5.iterations}")
    private int iterations;

    // 1.创建shiroFilter -- 拦截所有请求
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 给filter设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        // 配置系统受限资源
        HashMap<String, String> map = new HashMap<>();
        map.put("/user/login", "anon"); //anon 放行公共资源
        map.put("/user/register", "anon"); //anon 放行公共资源
        map.put("/register.jsp", "anon"); //anon 放行公共资源
        map.put("/order/save", "anon"); //anon 放行公共资源
        map.put("/**", "authc"); //authc 请求这个资源需要认证和授权

        // 默认认证界面路径（即使不写默认就是 /login.jsp）
        shiroFilterFactoryBean.setLoginUrl("/login.jsp");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    // 2.创建安全管理器
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("customRealm") Realm realm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();

        // 给安全管理器设置realm
        defaultWebSecurityManager.setRealm(realm);

        return defaultWebSecurityManager;
    }

    // 3.创建自定义realm
    @Bean("customRealm")
    public Realm getRealm() {
        CustomRealm customRealm = new CustomRealm();

        //修改默认的凭证匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5");
        credentialsMatcher.setHashIterations(iterations);
        customRealm.setCredentialsMatcher(credentialsMatcher);
        return customRealm;
    }
}
