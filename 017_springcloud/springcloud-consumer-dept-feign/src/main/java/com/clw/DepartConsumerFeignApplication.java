package com.clw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.clw.service"})
public class DepartConsumerFeignApplication {
    public static void main(String[] args) {
        SpringApplication.run(DepartConsumerFeignApplication.class, args);
    }
}
