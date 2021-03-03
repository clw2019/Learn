package com.clw.sys.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.clw.sys.mapper.RoleMapper;
import com.clw.sys.entity.Role;
import com.clw.sys.service.impl.RoleService;
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService{

}
