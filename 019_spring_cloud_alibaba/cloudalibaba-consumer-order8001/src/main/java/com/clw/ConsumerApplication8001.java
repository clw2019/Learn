package com.clw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: clw
 * @Description:
 * @Date: 2021/9/2 20:55
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerApplication8001 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication8001.class, args);
    }
}
