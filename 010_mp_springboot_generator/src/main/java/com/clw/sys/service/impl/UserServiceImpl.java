package com.clw.sys.service.impl;

import com.clw.sys.domain.User;
import com.clw.sys.mapper.UserMapper;
import com.clw.sys.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author clw
 * @since 2020-06-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
