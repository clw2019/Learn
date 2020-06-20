package com.clw.test;

import com.clw.Application;
import com.clw.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author: clw
 * @Description:
 * @Date: 2020/6/20 22:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestApp {

    @Test
    public void test01() {
        User user = new User();
        user.setId(2);
        user.deleteById();
        System.out.println("删除完成");
    }

    @Test
    public void test02() {
        User user = new User();
        List<User> userList = user.selectAll();
        System.out.println("selectAll : " + userList);
    }

    /**
     * 插入的时候，最少需要赋一个值
     */
    @Test
    public void test03() {
        User user = new User();
        user.setUsername("王小二");
        user.insert();
        System.out.println("添加成功");
    }
}
