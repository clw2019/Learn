package com.clw.mapper;

import com.clw.domain.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    Set<String> queryAllPermissionByUsername(@Param("username") String username);
}