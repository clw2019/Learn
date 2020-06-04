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

/**
 * @Author: clw
 * @Description:
 * @Date: 2020/6/4 16:06
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
        return "index";
    }

    @GetMapping("/register")
    public String register() {
        System.out.println("go to register page");
        return "register";
    }

    @PostMapping("/register")
    public String registerLogic(User user) {
        System.out.println("register logic");
        System.out.println("... " + user);
        Integer result = userService.register(user);
        if (result == 1) {
            return "redirect:/user/login";
        }
        return null;
    }

}
