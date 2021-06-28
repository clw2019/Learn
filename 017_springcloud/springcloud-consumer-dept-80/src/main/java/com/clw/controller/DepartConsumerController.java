package com.clw.controller;

import com.clw.entity.Depart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("deptConsumer")
public class DepartConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    /*@Autowired
    private RestOperations restTemplate;*/


//    private static final String REST_URL_PREFIX = "http://localhost:8081"; // 配置附在均衡 ribbon 之前 写死
    /**
     * Ribbon 和 Eureka 整合之后，客户端可以直接调用，不用关心IP和端口号
     */
    private static final String REST_URL_PREFIX = "http://SPRINGCLOUD-PROVIDER-DEPT"; // 配置负载均衡 ribbon 之后，访问服务端

    @GetMapping("/list")
    public List<Depart> getList() {
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/list", List.class);
    }

    @PostMapping("/add")
    public void addDept(@RequestBody Depart depart) {
        restTemplate.postForObject(REST_URL_PREFIX + "/dept/add", depart, void.class);
    }
}
