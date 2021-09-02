package com.clw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: clw
 * @Description:
 * @Date: 2021/8/31 21:44
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ProviderApplication9002 {
    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication9002.class, args);
    }
}
