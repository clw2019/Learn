package com.clw.controller;

import com.clw.common.CommonResult;
import com.clw.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/consumer")
@Slf4j
public class ConsumerOrderController {

//    private static final String URL_PREFIX = "http://localhost:8011"; // 单机
    private static final String URL_PREFIX = "http://CLOUD-PROVIDER-PAYMENT"; // 集群
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/list")
    public CommonResult list() {
        return restTemplate.getForObject(URL_PREFIX + "/payment/list", CommonResult.class);
    }

    @GetMapping("/get/{id}")
    public CommonResult getById(@PathVariable String id) {
        return restTemplate.getForObject(URL_PREFIX + "/payment/get/" + id, CommonResult.class);
    }

    @PostMapping("/add")
    public CommonResult add(@RequestBody Payment payment) {
        return restTemplate.postForObject(URL_PREFIX + "/payment/add", payment, CommonResult.class);
    }
}
