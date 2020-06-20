package com.clw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.clw.domain.User;

import java.util.List;

/**
 * @Author: clw
 * @Description:
 * @Date: 2020/6/19 23:56
 */
public interface UserMapper extends BaseMapper<User> {
    List<User> selectAll();
}
