package com.clw.service;

import com.clw.common.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PROVIDER-PAYMENT")
public interface IFeignClientService {

    @GetMapping("/payment/get/{id}")
    public CommonResult getById(@PathVariable("id") String id);

    @GetMapping("/payment/timeout")
    public String timeout();

}
