package com.clw.service.serviceImpl;

import com.clw.constant.MyConstant;
import com.clw.domain.User;
import com.clw.mapper.UserMapper;
import com.clw.service.UserService;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

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
    public User queryUserByUserName(String username) {
        User user = userMapper.queryUserByUserName(username);
        return user;
    }

    @Override
    public Integer register(User user) {
        String salt = UUID.randomUUID().toString();
        String s = new Sha256Hash(user.getUsername(), salt, MyConstant.ITERATERATIO).toBase64();
        user.setPassword(s);
        user.setSalt(salt);
        Integer result = userMapper.register(user);
        return result;
    }

}
