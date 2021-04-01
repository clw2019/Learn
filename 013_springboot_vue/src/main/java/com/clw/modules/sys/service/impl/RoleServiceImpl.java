package com.clw.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.clw.modules.sys.mapper.RoleMapper;
import com.clw.modules.sys.entity.Role;
import com.clw.modules.sys.service.RoleService;
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService{

}
