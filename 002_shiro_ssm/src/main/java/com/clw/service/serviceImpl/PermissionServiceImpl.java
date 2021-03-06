package com.clw.service.serviceImpl;

import com.clw.domain.Permission;
import com.clw.mapper.PermissionMapper;
import com.clw.service.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return permissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Permission record) {
        return permissionMapper.insert(record);
    }

    @Override
    public int insertSelective(Permission record) {
        return permissionMapper.insertSelective(record);
    }

    @Override
    public Permission selectByPrimaryKey(Integer id) {
        return permissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Permission record) {
        return permissionMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Permission record) {
        return permissionMapper.updateByPrimaryKey(record);
    }

    @Override
    public Set<String> queryAllPermissionByUsername(String username) {
        Set<String> permissionList = permissionMapper.queryAllPermissionByUsername(username);
        return permissionList;
    }

}
