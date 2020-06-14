package com.clw.service.impl;

import com.clw.domain.User;
import com.clw.service.UserService;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author: clw
 * @Description:
 * @Date: 2020/6/14 20:41
 */
@Service
public class UserServiceImpl extends JdbcDaoSupport implements UserService {
    ////使用@Resource不对，需要使用Getter和Setter，才可以将UserServiceImpl放到Spring中
    //private JdbcTemplate jdbcTemplate;
    //
    //public JdbcTemplate getJdbcTemplate() {
    //    return jdbcTemplate;
    //}
    //
    //public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    //    this.jdbcTemplate = jdbcTemplate;
    //}
    //
    //public User findUserById(Integer id) {
    //    String sql = "select * from user where id = ?";
    //    List<User> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class), id);
    //    return userList.isEmpty() ? null : userList.get(0);
    //}
    //
    //public User findUserByName(String username) {
    //    String sql = "select * from user where username = ?";
    //    List<User> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class), username);
    //    if (userList.isEmpty()) {
    //        return null;
    //    }
    //    if (userList.size() > 1) {
    //        throw new RuntimeException("结果集不唯一");
    //    }
    //    return userList.get(0);
    //}
    //
    //public void updateUser(User user) {
    //    String sql = "update user set username = ? where id = ?";
    //    jdbcTemplate.update(sql, user.getUsername(), user.getId());
    //}

    public User findUserById(Integer id) {
        String sql = "select * from user where id = ?";
        List<User> userList = getJdbcTemplate().query(sql, new BeanPropertyRowMapper<User>(User.class), id);
        return userList.isEmpty() ? null : userList.get(0);
    }

    public User findUserByName(String username) {
        String sql = "select * from user where username = ?";
        List<User> userList = getJdbcTemplate().query(sql, new BeanPropertyRowMapper<User>(User.class), username);
        if (userList.isEmpty()) {
            return null;
        }
        if (userList.size() > 1) {
            throw new RuntimeException("结果集不唯一");
        }
        return userList.get(0);
    }

    public void updateUser(User user) {
        String sql = "update user set username = ? where id = ?";
        getJdbcTemplate().update(sql, user.getUsername(), user.getId());
    }
}