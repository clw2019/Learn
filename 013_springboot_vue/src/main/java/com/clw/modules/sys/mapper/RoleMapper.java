package com.clw.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.clw.modules.sys.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {
    List<String> queryRoleSetByUsername(@Param("username") String username);
}