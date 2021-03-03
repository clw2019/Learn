package com.clw.sys.service.impl;

import com.clw.sys.entity.Permission;
import com.clw.sys.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.clw.sys.vo.LoginUser;

import java.util.List;

public interface UserService extends IService<User> {

    void register(User user);

    LoginUser findRolesByUsername(String username);

    List<Permission> findPermissionByRoleId(String roleId);
}

