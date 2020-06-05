package com.clw.service.impl;

import com.clw.constant.MyConstant;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.clw.mapper.UserMapper;
import com.clw.domain.User;
import com.clw.service.UserService;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    @Override
    public Integer register(User user) {
        String salt = UUID.randomUUID().toString();
        String password = new Sha256Hash(user.getPassword(), salt, MyConstant.ITERATERATIO).toBase64();
        user.setSalt(salt);
        user.setPassword(password);
        Integer result = userMapper.register(user);
        return result;
    }

    @Override
    public User queryUserByUserName(String username) {
        User user = userMapper.queryUserByUserName(username);
        return user;
    }


}
