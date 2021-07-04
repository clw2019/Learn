package com.clw.controller;

import javax.annotation.Resource;

import com.clw.service.IFeignClientHystrixService;
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
    public String getTimeout(@PathVariable("id") String id) {
        return hystrixService.getTimeout(id);
    }
}
