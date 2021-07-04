package com.clw.controller;

import javax.annotation.Resource;

import com.clw.service.IFeignClientHystrixService;
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

    public String getTimeoutHandler(@PathVariable("id") String id) {
        return "我是消费之80，对方系统繁忙，或者自己运行出错，请稍后再试。。。";
    }
}
