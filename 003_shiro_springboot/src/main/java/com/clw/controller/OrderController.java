package com.clw.controller;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: clw
 * @Description:
 * @Date: 2020/6/5 10:27
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @RequiresAuthentication
    @GetMapping("/query")
    public String query() {
        return "query";
    }

    @RequiresAuthentication
    @RequiresPermissions("order:add")
    @GetMapping("/add")
    public String add() {
        return "add";
    }

    @RequiresAuthentication
    @RequiresRoles(value = {"Admin", "超级管理员"}, logical = Logical.OR)
    @GetMapping("/update")
    public String update() {
        return "update";
    }

}
