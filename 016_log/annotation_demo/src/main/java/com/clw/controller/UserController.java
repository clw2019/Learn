package com.clw.controller;


import com.clw.annotation.ModuleType;
import com.clw.annotation.MyLog;
import com.clw.annotation.OperType;
import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @GetMapping("getStr")
    @MyLog(moduleType = ModuleType.USER, operType = OperType.RETRIEVE, description = "查询用戶")
    public String getStr(String name) {
        return name;
    }

    @PutMapping("addStr")
    @MyLog(moduleType = ModuleType.USER, operType = OperType.CREATE, description = "添加用戶")
    public String addStr(String name) {
        return name;
    }

    @DeleteMapping("deleteStr")
    @MyLog(moduleType = ModuleType.USER, operType = OperType.DELETE, description = "刪除用戶")
    public String deleteStr(String name)  {

        int a = 10 / 0;
        /*try {
            int a = 10 / 0;
        } catch (Throwable e) {
            e.printStackTrace();
        }*/

        /*try {
            int a = 10 / 0;
        } catch (Exception e) {
//            throw new Exception(e.getMessage());
            e.printStackTrace();
        }*/

        System.out.println("name = " + name);
        return name;
    }

    @PostMapping("updateStr")
    @MyLog(moduleType = ModuleType.USER, operType = OperType.UPDATE, description = "更新用戶")
    public String updateStr(String name) {
        return name;
    }
}
