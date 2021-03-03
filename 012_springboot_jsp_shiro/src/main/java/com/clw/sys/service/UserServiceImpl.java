package com.clw.sys.service;

import com.clw.sys.entity.Permission;
import com.clw.sys.entity.Role;
import com.clw.sys.vo.LoginUser;
import com.clw.util.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.clw.sys.entity.User;
import com.clw.sys.mapper.UserMapper;
import com.clw.sys.service.impl.UserService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Value("${md5.iterations}")
    private int iterations;

    @Resource
    private UserMapper userMapper;

    @Override
    public void register(User user) {
        //1.生成随机盐
        String salt = SaltUtils.getSalt(8);
        //2.将盐村存储到数据库
        user.setSalt(salt);
        //3.明文密码进行Md5 + salt + hash散列
        Md5Hash md5Hash = new Md5Hash(user.getPassword(), salt, iterations);
        user.setPassword(md5Hash.toHex());
        userMapper.insert(user);
    }

    @Override
    public LoginUser findRolesByUsername(String username) {
        return userMapper.findRolesByUsername(username);
    }

    @Override
    public List<Permission> findPermissionByRoleId(String roleId) {
        return userMapper.findPermissionByRoleId(roleId);
    }
}

