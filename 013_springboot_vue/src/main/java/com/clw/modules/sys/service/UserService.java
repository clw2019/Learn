package com.clw.modules.sys.service;

import com.clw.modules.sys.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

public interface UserService extends IService<User>{

    /**
    * @Author: clw
    * @Description: 通过用户名获取用户的权限结合
    * @Param: [username]
    * @return: java.util.Set<java.lang.String>
    * @Date: 2021/3/13 0:11
    */
    Set<String> queryPermissionByUsername(String username);

    /***
    * @Author: clw
    * @Description: 通过用户名获取用户的角色结合
    * @Param: [username]
    * @return: java.util.Set<java.lang.String>
    * @Date: 2021/3/13 22:22
    */
    Set<String> queryRoleSetByUsername(String username);
}
