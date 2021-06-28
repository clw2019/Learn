package com.clw;

import com.rule.LoadBalanceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@RibbonClient(name = "CLOUD-PROVIDER-PAYMENT", configuration = LoadBalanceConfig.class)
public class ConsumerOrderApplication80 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerOrderApplication80.class, args);
    }
}
