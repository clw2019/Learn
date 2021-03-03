package com.clw.sys.controller;

import com.clw.sys.entity.User;
import com.clw.sys.service.impl.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @Author: clw
 * @Description:
 * @Date: 2021/2/27 16:54
 */
@RequestMapping("/user")
@Controller
public class UserController {

    @Resource
    private UserService userService;

    /**
    * @Author: clw
    * @Description: 注册
    * @Param: [user]
    * @return: java.lang.String
    * @Date: 2021/2/27 17:23
    */
    @PostMapping("/register")
    public String register(User user) {
        try {
            userService.register(user);
            return "login";
        } catch (Exception e) {
            e.printStackTrace();
            return "register";
        }
    }

    /**
    * @Author: clw
    * @Description: 登录
    * @Param: [username, password]
    * @return: java.lang.String
    * @Date: 2021/2/27 17:27
    */
    @PostMapping("login")
    public String login(String username, String password) {

        // 获取主体对象
        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(new UsernamePasswordToken(username, password));
            return "redirect:/index.jsp";
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户名错误");
        } catch (IncorrectCredentialsException e) {
            e.getStackTrace();
            System.out.println("密码错误");
        }
        return "redirect:/login.jsp";
    }

    /**
    * @Author: clw
    * @Description: 退出
    * @Param: []
    * @return: java.lang.String
    * @Date: 2021/2/27 17:28
    */
    @GetMapping("logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login.jsp";
    }

}
