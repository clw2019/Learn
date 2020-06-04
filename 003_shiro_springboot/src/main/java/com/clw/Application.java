package com.clw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: clw
 * @Description:
 * @Date: 2020/6/4 11:37
 */
@SpringBootApplication
@MapperScan("com.clw.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
