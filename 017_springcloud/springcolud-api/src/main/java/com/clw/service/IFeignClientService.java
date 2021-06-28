package com.clw.service;

import com.clw.entity.Depart;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(value = "SPRINGCLOUD-PROVIDER-DEPT", fallbackFactory = DepartClientServiceFallBackFactory.class)
public interface IFeignClientService {

    @GetMapping("/dept/list")
    List<Depart> list();

    @GetMapping("/dept/queryById/{departNo}")
    Depart queryById(@PathVariable String departNo);

    @PostMapping("/dept/add")
    void addDept(Depart depart);

}
