package com.clw.controller;

import com.clw.result.CommonResult;
import com.clw.service.UserService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: clw
 * @Description:
 * @Date: 2020/6/6 18:44
 */
@RestController
public class LoginController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public CommonResult login(@RequestParam("username") String username, @RequestParam("password") String password) {
        System.out.println("username : " + username);
        System.out.println("password : " + password);
        return userService.login(username, password);
    }

    @GetMapping("/require_auth")
    @RequiresAuthentication
    public CommonResult requireAuth() {
        return CommonResult.success("你已经登录了", null);
    }

    @GetMapping("/require_role")
    @RequiresRoles("admin")
    public CommonResult requireRole() {
        return CommonResult.success("Admin权限", null);
    }

    @GetMapping("/require_permission")
    @RequiresPermissions(logical = Logical.AND, value = {"user", "order"})
    public CommonResult requirePermission() {
        return CommonResult.success("You are visiting permission require user,order", null);
    }
}
