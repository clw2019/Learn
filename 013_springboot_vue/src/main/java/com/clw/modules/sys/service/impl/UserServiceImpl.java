package com.clw.modules.sys.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.clw.modules.sys.entity.Permission;
import com.clw.modules.sys.entity.Role;
import com.clw.modules.sys.mapper.PermissionMapper;
import com.clw.modules.sys.mapper.RoleMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.clw.modules.sys.entity.User;
import com.clw.modules.sys.mapper.UserMapper;
import com.clw.modules.sys.service.UserService;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    @Resource
    private PermissionMapper permissionMapper;

    @Resource
    private RoleMapper roleMapper;

    @Override
    public Set<String> queryPermissionByUsername(String username) {
        HashSet<String> permissionSet = new HashSet<>();
        List<Permission> permissionList = permissionMapper.queryPermissionByUsername(username);
        for (Permission permission : permissionList) {
            if (BeanUtil.isNotEmpty(permission)) {
                permissionSet.add(permission.getPerms());
            }
        }
        return permissionSet;
    }

    @Override
    public Set<String> queryRoleSetByUsername(String username) {
        List<String> roleList = roleMapper.queryRoleSetByUsername(username);
        return new HashSet<>(roleList);
    }
}
