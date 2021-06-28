package com.clw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer // @EnableEurekaServer 服务的启动类，可以接收别人注册进来
public class EurekaApplication_7072 {

    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication_7072.class, args);
    }
}
