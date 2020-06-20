package com.clw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.clw.domain.User;

/**
 * @Author: clw
 * @Description: 此处继承的BaseMapper<User>, 泛型必须传入,否则报错: apache.ibatis.binding.BindingException: Invalid bound statement (not found):
 * @Date: 2020/6/20 21:53
 */
public interface UserMapper extends BaseMapper<User> {
}
