package com.clw.modules.config;

import com.clw.modules.shiro.ShiroRealm;
import com.clw.modules.shiro.filter.JwtFilter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: clw
 * @Description:
 * @Date: 2021/3/13 22:43
 */
@Slf4j
@Configuration
public class ShiroConfig {

    /***
    * @Author: clw
    * @Description:
     * Filter Chain 说明
     * 1.一个URL可以配置多个Filter。使用逗号分隔
     * 2.当设置多个过滤器时，全部验证通过，才视为通过
     * 3.部分过滤器可指定参数，如perms，roles
    * @Param: [securityManager]
    * @return: org.apache.shiro.spring.web.ShiroFilterFactoryBean
    * @Date: 2021/3/13 22:53
    */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactory(SecurityManager securityManager) {
        log.info("=====================[JwtFilter : shiroFilterFactory]=====================");

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        // CAS验证登录

        // 配置不会被拦截的链接，按顺序判断
        filterChainDefinitionMap.put("/sys/login", "anon");
        filterChainDefinitionMap.put("/user/getMenuList", "anon");

        // 添加自己的过滤器并命名为jwt
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("jwt", new JwtFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        // 定义过滤链，从上向下依次执行，一般将/**放在最下面
        filterChainDefinitionMap.put("/**", "jwt");

        // 未授权页面返回
        shiroFilterFactoryBean.setUnauthorizedUrl("/sys/common/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean("securityManager")
    public DefaultWebSecurityManager securityManager(ShiroRealm shiroRealm) {
        log.info("=====================[JwtFilter : securityManager]=====================");

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm);

        return securityManager;
    }
}
