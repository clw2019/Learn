package com.clw.jdbcTemplate;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * @Author: clw
 * @Description: JdbcTemplate的最基本用法
 * @Date: 2020/6/14 17:29
 */
public class JdbcTemplateDemo {

    @Test
    public void test01() {
        //准备数据源，spring的内置数据源
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/006_spring_jdbctemplate01?serverTimezone=Asia/Shanghai");
        dataSource.setUsername("root");
        dataSource.setPassword("wj113");

        //1.创建JdbcTemplate对象
        JdbcTemplate jt = new JdbcTemplate();
        //给JdbcTemplate设置数据源
        jt.setDataSource(dataSource);
        //2.执行操作
        jt.execute("insert into user(username, password, salt) values ('路明非', '123456', '123')");
    }
}
