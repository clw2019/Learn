package com.clw.controller;

import javax.annotation.Resource;

import com.clw.common.CommonResult;
import com.clw.service.IFeignClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: clw
 * @Description:
 * @Date: 2021/6/30 22:44
 */
@RestController
@RequestMapping("/feignConsumer")
public class ConsumerOrderFeignController {

    @Resource
    private IFeignClientService feignClientService;

    @GetMapping("/get/{id}")
    public CommonResult getById(@PathVariable("id") String id) {
        return feignClientService.getById(id);
    }

    @GetMapping("/timeout")
    public String timeout() {
        return feignClientService.timeout();
    }

}
