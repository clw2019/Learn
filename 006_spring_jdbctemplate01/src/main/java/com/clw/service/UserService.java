package com.clw.service;

import com.clw.domain.User;

/**
 * @Author: clw
 * @Description:
 * @Date: 2020/6/14 20:25
 */
public interface UserService {
    User findUserById(Integer id);
    User findUserByName(String username);
    void updateUser(User user);
}
