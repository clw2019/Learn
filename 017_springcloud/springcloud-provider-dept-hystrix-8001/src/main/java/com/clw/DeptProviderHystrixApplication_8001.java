package com.clw;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("com.clw.mapper")
@EnableEurekaClient // 把客户端注册到服务端
@EnableDiscoveryClient // 服务发现
@EnableCircuitBreaker // 开启断路器
public class DeptProviderHystrixApplication_8001 {
    public static void main(String[] args) {
        SpringApplication.run(DeptProviderHystrixApplication_8001.class, args);
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean () {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new HystrixMetricsStreamServlet());
        servletRegistrationBean.addUrlMappings("/actuator/hystrix.stream");
        return servletRegistrationBean;
    }
}
