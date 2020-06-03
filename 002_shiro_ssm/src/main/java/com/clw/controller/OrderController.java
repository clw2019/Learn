package com.clw.controller;

import org.apache.shiro.authz.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: clw
 * @Description:
 * @Date: 2020/6/3 20:28
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @RequiresUser   //登录且记住我
    @RequestMapping("/query")
    public String queryOrder() {
        System.out.println("query order...");
        return "index";
    }

    @RequiresAuthentication     //只有登录才能访问
    @RequiresRoles(value = {"班长", "同学"}, logical = Logical.OR)  //只有其中一个角色才能
    @RequestMapping("/save")
    public String saveOrder() {
        System.out.println("save order...");
        return "index";
    }

    @RequiresAuthentication     //只有登录才能访问
    //@RequiresPermissions("user:delete")
    @RequiresPermissions(value = {"user:delete", "user:query"}, logical = Logical.AND)  //二者权限都具备
    @RequestMapping("/delete")
    public String deleteOrder() {
        System.out.println("delete order...");
        return "index";
    }

    @RequiresAuthentication     //只有登录才能访问
    @RequestMapping("/update")
    public String updateOrder() {
        System.out.println("update order...");
        return "index";
    }
}
