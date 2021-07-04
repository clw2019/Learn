package com.clw.controller;

import javax.annotation.Resource;

import com.clw.service.IFeignClientHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: clw
 * @Description:
 * @Date: 2021/7/3 20:07
 */
@RestController
@RequestMapping("/orderHystrix")
@DefaultProperties(defaultFallback = "defaultTimeoutHandler")
public class OrderHystrixController {

    @Resource
    private IFeignClientHystrixService hystrixService;

    @GetMapping("/getOk/{id}")
    public String getOk(@PathVariable("id") String id) {
        return hystrixService.getOk(id);
    }

    @GetMapping("/getTimeout/{id}")
    @HystrixCommand(fallbackMethod = "getTimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "15000")
    })
    public String getTimeout(@PathVariable("id") String id) {
        return hystrixService.getTimeout(id);
    }

    @GetMapping("/getTimeout2/{id}")
    @HystrixCommand
    public String getTimeout2(@PathVariable("id") String id) {
        return hystrixService.getTimeout(id);
    }

    public String getTimeoutHandler(@PathVariable("id") String id) {
        return "我是消费之80，对方系统繁忙，或者自己运行出错，请稍后再试。。。";
    }

    public String defaultTimeoutHandler() {
        return "Global default HystrixCommand。。。";
    }

    // 服务熔断
    @GetMapping("/circuitBreaker/{id}")
    @HystrixCommand(fallbackMethod = "circuitBreakerFallBack", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), // 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), // 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"), // 失败率达到多少后跳闸
    })
    public String circuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("===== id 不能为负数 =====");
        }
        return Thread.currentThread().getName() + " 调用成功。。。";
    }

    public String circuitBreakerFallBack(@PathVariable("id") Integer id) {
        return "===== id 不能为负数, 请稍后再试 =====";
    }
}
