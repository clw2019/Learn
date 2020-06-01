package com.test;

import com.clw.domain.User;
import com.clw.mapper.UserMapper;
import com.clw.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: clw
 * @Description:
 * @Date: 2020/6/1 18:49
 */
public class RegisterTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring_config.xml");
        UserMapper userMapper = context.getBean("userMapper", UserMapper.class);
        userMapper.register(new User(null, "123", "123", null));
    }
}
