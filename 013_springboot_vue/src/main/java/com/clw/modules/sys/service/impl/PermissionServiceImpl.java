package com.clw.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.clw.modules.sys.mapper.PermissionMapper;
import com.clw.modules.sys.entity.Permission;
import com.clw.modules.sys.service.PermissionService;
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService{

}
