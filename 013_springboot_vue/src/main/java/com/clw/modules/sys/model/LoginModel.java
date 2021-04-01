package com.clw.modules.sys.model;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: clw
 * @Description: 接收登录数据的实体
 * @Date: 2021/3/13 23:44
 */
@Data
@ApiModel(value = "登录对象", description = "登录对象")
public class LoginModel {
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("密码")
    private String password;

}
