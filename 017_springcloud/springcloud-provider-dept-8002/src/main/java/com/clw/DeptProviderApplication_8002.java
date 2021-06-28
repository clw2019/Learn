package com.clw;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import javax.servlet.Servlet;

@SpringBootApplication
@MapperScan("com.clw.mapper")
@EnableEurekaClient // 把客户端注册到服务端
@EnableDiscoveryClient // 服务发现
public class DeptProviderApplication_8002 {
    public static void main(String[] args) {
        SpringApplication.run(DeptProviderApplication_8002.class, args);
    }
}
