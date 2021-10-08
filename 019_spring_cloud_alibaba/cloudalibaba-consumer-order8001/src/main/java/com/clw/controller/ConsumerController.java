package com.clw.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Author: clw
 * @Description:
 * @Date: 2021/9/2 21:13
 */
@RestController
public class ConsumerController {

    @Resource
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    private String serviceURL;

    @RequestMapping("/consumer/payment/nacos/{id}")
    public String consumer(@PathVariable("id") String id) {
        return restTemplate.getForObject(serviceURL + "/payment/nacos/" + id, String.class);
    }
}
