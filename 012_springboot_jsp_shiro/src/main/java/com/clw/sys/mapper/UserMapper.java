package com.clw.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.clw.sys.entity.Permission;
import com.clw.sys.entity.Role;
import com.clw.sys.entity.User;
import com.clw.sys.vo.LoginUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    LoginUser findRolesByUsername(@Param("username") String username);

    List<Permission> findPermissionByRoleId(@Param("roleId") String roleId);
}