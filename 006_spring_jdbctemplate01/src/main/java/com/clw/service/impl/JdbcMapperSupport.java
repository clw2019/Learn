//package com.clw.service.impl;
//
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import javax.sql.DataSource;
//
///**
// * @Author: clw
// * @Description: 抽取UserService中重复的代码,此类Spring已经帮我们写好了,即JdbcDaoSupport,但是继承此类后，使用注解开发配置会更加繁琐
// * @Date: 2020/6/14 23:16
// */
//public class JdbcMapperSupport {
//    private JdbcTemplate jdbcTemplate;
//
//    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public JdbcTemplate getJdbcTemplate() {
//        return jdbcTemplate;
//    }
//
//
//    public void setDataSource(DataSource dataSource) {
//        if (jdbcTemplate == null) {
//            jdbcTemplate = createJdbcTemplate(dataSource);
//        }
//    }
//
//    private JdbcTemplate createJdbcTemplate(DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
//}
