package com.clw;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;

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

    /**
     * 服务监控配置
     * 此配置是为了服务监控而配置，与服务本省容错无关，SpringCloud升级之后的坑,
     * ServletRegistrationBean 因为SpringBoot的默认路径不是"/hystrix.stream",
     * 只要在自己的项目里配置上下文的Servlet
     */
    @Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean<HystrixMetricsStreamServlet> registrationBean = new ServletRegistrationBean<>(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
}
