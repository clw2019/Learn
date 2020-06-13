package com.clw.config;

import com.clw.shiro.JWTFilter;
import com.clw.shiro.MyRealm;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: clw
 * @Description:
 * @Date: 2020/6/6 18:14
 */
@Configuration
public class ShiroConfig {
    /**
     * ShiroFilterFactoryBean，是个factorybean，为了生成ShiroFilter。
     * 它主要保持了三项数据，securityManager，filters，filterChainDefinitionManager。
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 添加自己的过滤器并且取名为jwt
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("jwt", new JWTFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        // 自定义URL规则
        Map<String, String> filterRuleMap = new HashMap<>();
        filterRuleMap.put("/**", "jwt");
        filterRuleMap.put("/login", "anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterRuleMap);
        return shiroFilterFactoryBean;
    }

    /**
     * SecurityManager，权限管理，这个类组合了登陆，登出，权限，session的处理，是个比较重要的类
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 自定义realm
        securityManager.setRealm(realm());
        // 关闭自带session, 因为在前后分离项目中前端是无法获取到后端 Session 的，即无法实现用户登录状态的同步
        DefaultSessionStorageEvaluator evaluator = new DefaultSessionStorageEvaluator();
        evaluator.setSessionStorageEnabled(false);
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        subjectDAO.setSessionStorageEvaluator(evaluator);
        // 记住我管理器
        //securityManager.setRememberMeManager(cookieRememberMeManager());
        return securityManager;
    }

    /**
     * ShiroRealm，这是个自定义的认证类，继承自AuthorizingRealm，
     * 负责用户的认证和权限的处理
     */
    @Bean
    public Realm realm() {
        MyRealm myRealm = new MyRealm();
        // 注入加密
        //myRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myRealm;
    }

    ///**
    // * LifecycleBeanPostProcessor 是 DestructionAwareBeanPostProcessor的子类。
    // * 负责org.apache.shiro.util.Initializable类型bean的生命周期的，初始化和销毁。
    // * 主要是AuthorizingRealm类的子类，以及EhCacheManager类。
    // * ps: 在spring-shiro中的ShiroBeanConfiguration已经配置过了，无需再次配置
    // */
    //@Bean
    //public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
    //    return new LifecycleBeanPostProcessor();
    //}

    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
     * DefaultAdvisorAutoProxyCreator，Spring的一个bean，由Advisor决定对哪些类的方法进行AOP代理
     */
    @Bean
    //@DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    /**
     * AuthorizationAttributeSourceAdvisor，shiro里实现的Advisor类，
     * 内部使用AopAllianceAnnotationsAuthorizingMethodInterceptor来拦截用以下注解的方法。
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * HashedCredentialsMatcher，这个类是为了对密码进行编码的，
     * 防止密码在数据库里明码保存，当然在登陆认证的时候，
     * 这个类也负责对form里输入的密码进行编码。
     */
    //@Bean
    //public HashedCredentialsMatcher hashedCredentialsMatcher() {
    //    HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
    //    credentialsMatcher.setHashAlgorithmName("SHA-256");
    //    credentialsMatcher.setHashIterations(MyConstant.ITERATERATIO);
    //    // true: hexed, false: base64
    //    credentialsMatcher.setStoredCredentialsHexEncoded(false);
    //    return credentialsMatcher;
    //}

    /**
     * cookie对象;
     * rememberMeCookie()方法是设置Cookie的生成模版，比如cookie的name，cookie的有效时间等等。
     */
    //@Bean
    //public SimpleCookie rememberMeCookie() {
    //    // 默认就是 rememberMe
    //    SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
    //    // cookie生命周期，单位：秒，默认：31536000 即一年 604800 即一周 7 天
    //    simpleCookie.setMaxAge(604800);
    //    // 默认就是 true，cookie只在http请求中可用
    //    simpleCookie.setHttpOnly(true);
    //    return simpleCookie;
    //}

    /**
     * 记住我管理器
     */
    //@Bean
    //public CookieRememberMeManager cookieRememberMeManager() {
    //    CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
    //    cookieRememberMeManager.setCookie(rememberMeCookie());
    //    //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
    //    //cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
    //    return cookieRememberMeManager;
    //}
}
