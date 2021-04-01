package com.clw.modules.shiro.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @Author: clw
 * @Description: 登录拦截器
 * @Date: 2021/3/8 23:02
 */
@Slf4j
public class JwtFilter extends BasicHttpAuthenticationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        log.info("=====================[JwtFilter : isAccessAllowed]=====================");
        return super.isAccessAllowed(request, response, mappedValue);
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        log.info("=====================[JwtFilter : executeLogin]=====================");
        return super.executeLogin(request, response);
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        log.info("=====================[JwtFilter : preHandle]=====================");
        return super.preHandle(request, response);
    }
}
