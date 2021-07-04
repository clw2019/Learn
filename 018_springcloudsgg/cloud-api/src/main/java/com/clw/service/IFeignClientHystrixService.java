package com.clw.service;

import com.clw.service.impl.HystrixFallbackServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PROVIDER-PAYMENT-HYSTRIX", fallback = HystrixFallbackServiceImpl.class)
public interface IFeignClientHystrixService {

    @GetMapping("/hystrix/getOk/{id}")
    public String getOk(@PathVariable("id") String id);

    @GetMapping("/hystrix/getTimeout/{id}")
    public String getTimeout(@PathVariable("id") String id);
}
