package com.clw;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: clw
 * @Description:
 * @Date: 2021/3/30 22:57
 */
public class TestMain {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        User user1 = context.getBean("user", User.class);
        User user2 = context.getBean("user", User.class);
        User user3 = context.getBean("user1", User.class);
        User user4 = context.getBean("user1", User.class);
        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user3);
        System.out.println(user4);

        System.out.println("user1 == user2 : ");
        System.out.println(user1 == user2);
        System.out.println("user3 == user4 : ");
        System.out.println(user3 == user4);
    }
}
