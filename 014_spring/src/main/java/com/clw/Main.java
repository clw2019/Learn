package com.clw;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.function.Supplier;

/**
 * @Author: clw
 * @Description:
 * @Date: 2021/3/25 21:08
 */
public class Main {
    public static void main(String[] args) {
        /*
         * 对象:
         * Bean 肯定是对象
         */
        User user = new User();
        /*
        * JavaBean:
        * 1.属性私有
        * 2.提供对应的getter、setter方法
        */
        String name = user.getName();

        /*
         * SpringBean:
         * 由Spring框架生成的
         */
        // 声名式 1
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        User user1 = applicationContext.getBean("user", User.class);
        System.out.println(user1);

        // 声名式 2
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(Config.class);
        User user2 = annotationConfigApplicationContext.getBean("getUser", User.class);
        System.out.println(user2);

        // 声名式 3
        AnnotationConfigApplicationContext aConfigApplicationContext = new AnnotationConfigApplicationContext(Config2.class);
        User user3 = aConfigApplicationContext.getBean("user3", User.class);
        System.out.println(user3);

        // 编程式
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
        beanDefinition.setBeanClass(User.class);
        context.registerBeanDefinition("user4", beanDefinition);
        context.refresh();
        User user4 = context.getBean("user4", User.class);
        System.out.println(user4);

        AnnotationConfigApplicationContext context2 = new AnnotationConfigApplicationContext();
        AbstractBeanDefinition beanDefinition2 = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
        /*
         * 表面定义一个Bean对象
         * 其实 实际 生成了 两个Bean对象: ZhouyuFactoryBean(的名字 &user) 和 Person 对象(的名字user)
        */
        beanDefinition2.setBeanClass(ZhouyuFactoryBean.class);
        context2.registerBeanDefinition("user", beanDefinition2);
        context2.refresh();
        // ZhouyuFactoryBean user5 = context2.getBean("user", ZhouyuFactoryBean.class); // 错误
        // ZhouyuFactoryBean user5 = context2.getBean("&user", ZhouyuFactoryBean.class); // 正确
        Person user5 = context2.getBean("user", Person.class); // 正确
        System.out.println(user5);

        AnnotationConfigApplicationContext context3 = new AnnotationConfigApplicationContext();
        context3.registerBean(User.class);
        context3.refresh();
        User user6 = context3.getBean("user3", User.class);
        System.out.println(user6);

        AnnotationConfigApplicationContext context4 = new AnnotationConfigApplicationContext();
        context4.registerBean(Person.class, new Supplier<Person>() {
            @Override
            public Person get() {
                Person person = new Person();
                person.setName("xxxx");
                return person;
            }
        });
        context4.refresh();
        Person user7 = context4.getBean("person", Person.class);
        System.out.println(user7.getName());
    }
}
