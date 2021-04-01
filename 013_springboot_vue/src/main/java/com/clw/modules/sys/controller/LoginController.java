package com.clw.modules.sys.controller;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.clw.common.constant.MyConstant;
import com.clw.common.result.Result;
import com.clw.modules.shiro.JwtToken;
import com.clw.modules.sys.entity.User;
import com.clw.modules.sys.model.LoginModel;
import com.clw.modules.sys.service.UserService;
import com.clw.modules.util.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: clw
 * @Description:
 * @Date: 2021/3/13 23:16
 */
@Slf4j
@Api(tags = "用户登录")
@RestController
@RequestMapping("/sys")
public class LoginController {

    @Resource
    private UserService userService;

    @ApiOperation("登录接口")
    @PostMapping("/login")
    public Result<String> login(@RequestBody LoginModel loginModel) {
        String username = loginModel.getUsername();
        String password = loginModel.getPassword();
        User user = userService.getOne(new LambdaQueryWrapper<>(User.class).eq(User::getUsername, username));
        if (user == null) {
            return Result.fail(MyConstant.USER_COUNT_NOT_FOUND);
        }
        if (MyConstant.DEL_FLAG_1.toString().equals(user.getDelFlag())) {
            return Result.fail(MyConstant.USER_CANCELED);
        }
        String token = JwtUtils.sign(username, MyConstant.TOKEN_KEY);
        JwtToken jwtToken = new JwtToken(token);
        SecurityUtils.getSubject().login(jwtToken);
        return Result.success(token);
    }

}
