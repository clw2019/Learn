package com.clw.mapper;

import com.clw.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    Set<String> queryAllRolesByUsername(@Param("username") String username);
}