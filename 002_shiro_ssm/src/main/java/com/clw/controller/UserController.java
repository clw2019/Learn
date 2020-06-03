package com.clw.controller;

import com.clw.domain.User;
import com.clw.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @Author: clw
 * @Description:
 * @Date: 2020/5/28 13:29
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        System.out.println("go to login page");
        return "login";
    }

    @PostMapping("/login")
    public String loginLogic(User user) {
        System.out.println("login logic");
        // 获取subject 调用login
        Subject subject = SecurityUtils.getSubject();
        // 创建登录令牌
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        //开启记住我
        token.setRememberMe(true);
        // 登录失败抛出异常，则交由异常解析器处理
        subject.login(token);
        return "index";
    }

    // 只有登录，且有权限才可访问
    @GetMapping("/all")
    public String queryAllUsers(HttpSession session) {
        System.out.println("query all users");
        session.setAttribute("name", "且随疾风前行");
        return "index";
    }

    @GetMapping("/perms/error")
    public String permsError() {
        System.out.println("权限不足");
        return "perm_error";
    }

    @GetMapping("/register")
    public String register() {
        System.out.println("go to register");
        return "register";
    }

    @PostMapping("/register")
    public String registerLogic(User user) {
        Integer result = userService.register(user);
        if (result == 1) {
            return "redirect:/user/login";
        }
        return null;
    }
}
