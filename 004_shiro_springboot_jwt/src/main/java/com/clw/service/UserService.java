package com.clw.service;

import com.clw.domain.User;
import com.clw.result.CommonResult;

public interface UserService{


    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User queryUserByUserName(String username);

    CommonResult login(String username, String password);
}
