package com.clw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

/**
 * @Author: clw
 * @Description:
 * @Date: 2021/7/3 19:37
 */
@SpringBootApplication
@EnableCircuitBreaker // 开启断路器
public class PaymentHystrixApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaymentHystrixApplication.class, args);
    }
}
