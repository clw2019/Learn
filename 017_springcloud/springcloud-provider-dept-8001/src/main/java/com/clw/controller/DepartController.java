package com.clw.controller;

import com.clw.entity.Depart;
import com.clw.service.IDepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("dept")
@RestController
public class DepartController {

    @Autowired
    private IDepartService departService;

    // 获取一些配置信息，得到具体的微服务
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/list")
    public List<Depart> getDepart(Depart depart) {
        return departService.list();
    }

    @GetMapping("/queryById/{deptNo}")
    public Depart queryById(@PathVariable String deptNo) {
        return departService.getById(deptNo);
    }

    @PostMapping("/add")
    public void addDepart(@RequestBody Depart depart) {
        departService.save(depart);
    }

    // 注册进来的微服务，获取一些信息
    @GetMapping("/discovery")
    public Object discovery() {
        // 获取微服务的清单
        List<String> services = discoveryClient.getServices();
        System.out.println("discovery => services: " + services);
        // 得到一个具体的微服务信息
        List<ServiceInstance> instances = discoveryClient.getInstances("SPRINGCLOUD-PROVIDER-DEPT");
        for (ServiceInstance instance : instances) {
            System.out.println(
                    instance.getHost() + "\t" +
                    instance.getPort() + "\t" +
                    instance.getUri() + "\t" +
                    instance.getServiceId()
            );
        }
        return this.discoveryClient;
    }
}
