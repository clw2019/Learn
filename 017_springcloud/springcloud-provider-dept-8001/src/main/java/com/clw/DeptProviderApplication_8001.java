package com.clw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan("com.clw.mapper")
@EnableEurekaClient // 把客户端注册到服务端
@EnableDiscoveryClient // 服务发现
public class DeptProviderApplication_8001 {
    public static void main(String[] args) {
        SpringApplication.run(DeptProviderApplication_8001.class, args);
    }
}
