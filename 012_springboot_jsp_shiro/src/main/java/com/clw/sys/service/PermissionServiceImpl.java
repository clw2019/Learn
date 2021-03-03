package com.clw.sys.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.clw.sys.entity.Permission;
import com.clw.sys.mapper.PermissionMapper;
import com.clw.sys.service.impl.PermissionService;
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService{

}
