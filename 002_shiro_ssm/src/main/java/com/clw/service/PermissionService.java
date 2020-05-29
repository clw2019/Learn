package com.clw.service;

import com.clw.domain.Permission;

import java.util.Set;

public interface PermissionService{


    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    Set<String> queryAllPermissionByUsername(String username);

}
