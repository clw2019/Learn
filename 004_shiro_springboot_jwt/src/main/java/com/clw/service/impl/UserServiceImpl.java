package com.clw.service.impl;

import com.clw.constant.MyConstant;
import com.clw.domain.User;
import com.clw.mapper.UserMapper;
import com.clw.result.CommonResult;
import com.clw.service.UserService;
import com.clw.shiro.JWTToken;
import com.clw.util.JwtTokenUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    //@Resource
    //private JwtTokenUtil jwtTokenUtil;
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
    public CommonResult login(String username, String password) {
        User user = userMapper.queryUserByUserName(username);
        if(user != null) {
            String salt = user.getSalt();
            String s = new Sha256Hash(user.getPassword(), salt, MyConstant.ITERATERATIO).toBase64();
            Map<String, Object> map = new HashMap<>();
            map.put("username", user.getUsername());
            String token = JwtTokenUtil.generateToken(map);
            System.out.println("token...... " + token);
            JWTToken jwtToken = new JWTToken(token);
            System.out.println("jwtToken...... " + jwtToken);
            SecurityUtils.getSubject().login(jwtToken);
            return CommonResult.success("login success", jwtToken);
        }
        return null;
    }

}
