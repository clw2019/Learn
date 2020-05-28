package com.clw.resolver;

import lombok.val;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
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
        }
        return mv;
    }
}
