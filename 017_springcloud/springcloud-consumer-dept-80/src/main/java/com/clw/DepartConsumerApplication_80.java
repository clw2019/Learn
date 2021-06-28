package com.clw;

import com.clw.config.CustomRibbonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
//@EnableDiscoveryClient // 服务发现
@RibbonClient(name = "SPRINGCLOUD-PROVIDER-DEPT", configuration = CustomRibbonConfig.class)
public class DepartConsumerApplication_80 {
    public static void main(String[] args) {
        SpringApplication.run(DepartConsumerApplication_80.class, args);
    }
}
