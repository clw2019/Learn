package com.clw.service;

import com.clw.domain.Role;

import java.util.Set;

public interface RoleService{


    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    Set<String> queryAllRolesByUsername(String username);

}
