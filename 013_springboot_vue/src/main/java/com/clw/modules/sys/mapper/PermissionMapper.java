package com.clw.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.clw.modules.sys.entity.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionMapper extends BaseMapper<Permission> {
    List<Permission> queryPermissionByUsername(@Param("username") String username);
}