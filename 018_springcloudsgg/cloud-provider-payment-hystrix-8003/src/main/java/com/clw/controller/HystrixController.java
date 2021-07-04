package com.clw.controller;

import java.util.concurrent.TimeUnit;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: clw
 * @Description:
 * @Date: 2021/7/3 19:39
 */
@RestController
@RequestMapping("/hystrix")
public class HystrixController {

    @GetMapping("/getOk/{id}")
    public String paymentInfoOk(@PathVariable("id") String id) {
        return "线程池: " + Thread.currentThread().getName() + " paymentInfoOk, id => " + id;
    }

    @GetMapping("/getTimeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentInfoTimeoutHandler",commandProperties = {
            // 超时时间 3 秒
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfoTimeout(@PathVariable("id") String id) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池: " + Thread.currentThread().getName() + " paymentInfoTimeout, id => " + id;
    }

    public String paymentInfoTimeoutHandler(String id) {
        return "线程池: " + Thread.currentThread().getName() + " fallbackMethod - paymentInfoTimeoutHandler, id => " + id;
    }

}
