package com.clw.resolver;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: clw
 * @Description:
 * @Date: 2020/5/28 14:31
 */
public class MyExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        System.out.println(e.getClass());
        e.printStackTrace();//开发时必须的
        ModelAndView mv = new ModelAndView();
        if (e instanceof IncorrectCredentialsException || e instanceof UnknownAccountException) {
            // 跳转到登录页面，重新登录
            mv.setViewName("redirect:/user/login");
        } else if(e instanceof UnauthorizedException){// 角色不足  权限不足
            //跳转权限不足的页面
            mv.setViewName("redirect:/user/perms/error");
        } else if(e instanceof UnauthenticatedException){//没有登录 没有合法身份
            //跳转登录页面，重新登录
            mv.setViewName("redirect:/user/login");
        }
        return mv;
    }
}
