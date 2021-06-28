package com.clw.controller;

import com.clw.entity.Depart;
import com.clw.service.IFeignClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("deptConsumer")
public class DepartConsumerController {

    @Autowired
    private IFeignClientService feignClientService;

    @GetMapping("/list")
    public List<Depart> getList() {
        return feignClientService.list();
    }

    @GetMapping("/queryById/{departNo}")
    public Depart queryById(@PathVariable String departNo) {
        return feignClientService.queryById(departNo);
    }

    @PostMapping("/add")
    public void addDept(@RequestBody Depart depart) {
        feignClientService.addDept(depart);
    }
}
