package com.clw.sys.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @Author: clw
 * @Description:
 * @Date: 2021/2/28 15:54
 */
@RequestMapping("order")
@Controller
public class OrderController {

    @GetMapping("save")
    // @RequiresPermissions("admin:add:*")
    @RequiresRoles(value = {"user", "admin"})  // 同时拥有
    public String save() {
        //获取主题对象
        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole("user")) {
            System.out.println("保存成功");
        } else {
            System.out.println("权限不足");
        }
        return "index";
    }
}
