package com.clw.jdbcTemplate;

import com.clw.domain.User;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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
        //execute：可以执行所有SQL语句，一般用于执行DDL语句。
        //update：用于执行INSERT、UPDATE、DELETE等DML语句。
        //queryXxx：用于DQL数据查询语句。
        jt.update("insert into user(username, password, salt) values ('楚子航', '123456', '123')");
        jt.update("insert into user(username, password, salt) values ('陈墨瞳', '123456', '123')");
        String sql = "insert into user(username, password, salt) values (?, ?, ?)";
        jt.update(sql, "夏弥", "123456", "123");
        jt.update("update user set username = ? where id = ?", "路鸣泽", 6);
        jt.update("delete from user where id = ?", 8);

        List<Map<String, Object>> maps = jt.queryForList("select * from user where id > ?", 5);
        System.out.println("ListMap... " + maps);
        Map<String, Object> map = jt.queryForMap("select * from user where id = ?", 6);
        System.out.println("map... " + map);
        //查询所有的两种写法：推荐第二种，不用实现RowMapper
        List<User> userList = jt.query("select * from user where id > ?", new UserRowMapper(), 5);
        System.out.println("userList... " + userList);
        List<User> query = jt.query("select * from user where id > ?", new BeanPropertyRowMapper<User>(User.class), 5);
        System.out.println("query... " + query);
        //查询一个
        List<User> query1 = jt.query("select * from user where id = ?", new BeanPropertyRowMapper<User>(User.class), 9);
        System.out.println("查询一条结果... " + query1.get(0));
        //查询返回一行一列(使用聚合函数，但不加group by)
        Integer integer = jt.queryForObject("select count(*) from user where id >?", Integer.class, 5);
        System.out.println("integer... " + integer);
    }
}
/**
 * 定义User的封装策略
 */
class UserRowMapper implements RowMapper<User> {
    /**
     * 把结果集中的数据封装到User中，然后由Spring把每个User封装到List集合中
     * @param resultSet
     * @param i
     * @return
     * @throws SQLException
     */
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        return user;
    }
}
