package com.clw.controller;

import com.clw.entity.Depart;
import com.clw.service.IDepartService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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

    @GetMapping("queryById/{deptNo}")
    @HystrixCommand(fallbackMethod = "hystrixQueryById")
    public Depart queryById(@PathVariable String deptNo) {
        Depart depart = departService.getById(deptNo);
        if (depart == null) {
            throw new RuntimeException("depart is null");
        }
        return depart;
    }

    public Depart hystrixQueryById(@PathVariable String deptNo) {
        return new Depart()
                .setDeptNo(deptNo)
                .setDeptName("depNo => " + deptNo + "沒有对应的信息，null--@Hystrix")
                .setDbName("no this database in MySQL");
    }
}
