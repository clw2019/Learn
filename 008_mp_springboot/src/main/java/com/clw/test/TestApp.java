package com.clw.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clw.Application;
import com.clw.domain.User;
import com.clw.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author: clw
 * @Description:
 * @Date: 2020/6/20 19:14
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestApp {

    @Resource
    private UserMapper userMapper;

    @Test
    public void test01() {
        int i = userMapper.insert(new User(null, "路明非", 18, "@163.com"));
        System.out.println(i);
    }

    /**
     * SpringBoot + MybatisPlus,和Spring + MybatisPlus主要不同之处在分页配置上
     */
    @Test
    public void test02() {
        System.out.println("" + userMapper.selectById(1));
        Page<User> userPage = userMapper.selectPage(new Page<>(1, 2), new QueryWrapper<>());
        List<User> userList = userPage.getRecords();
        System.out.println("selectPage : " + userList);
        Page<Map<String, Object>> mapPage = userMapper.selectMapsPage(new Page<>(1, 2), new QueryWrapper<>());
        List<Map<String, Object>> mapList = mapPage.getRecords();
        System.out.println("selectMapsPage : " + mapList);
    }
}
