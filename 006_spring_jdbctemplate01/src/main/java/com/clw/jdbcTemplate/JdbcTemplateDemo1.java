package com.clw.jdbcTemplate;

import com.clw.domain.User;
import com.clw.service.UserService;
import com.clw.service.impl.UserServiceImpl;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: clw
 * @Description:
 * @Date: 2020/6/14 20:23
 */
public class JdbcTemplateDemo1 {
    @Test
    public void test() {
        // 1.读取配置文件
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring_bean.xml");
        // 2.获取对象
        UserService userService = context.getBean("userService", UserServiceImpl.class);
        // 3.执行逻辑
        User user = userService.findUserById(7);
        System.out.println("user... " + user);

    }
}
