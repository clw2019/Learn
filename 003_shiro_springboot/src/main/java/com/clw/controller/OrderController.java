package com.clw.controller;

import org.apache.shiro.authz.annotation.*;
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

    //@RequiresUser  //登录且记住我，ps:没有起作用，可在ShiroConfig的过滤器中配置
    @GetMapping("/query")
    public String query() {
        return "query";
    }

    @RequiresAuthentication  //只有登录才能访问。登录记住我后，关闭浏览器，只能访问带@RequiresUser的，不能访问带@RequiresAuthentication
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
