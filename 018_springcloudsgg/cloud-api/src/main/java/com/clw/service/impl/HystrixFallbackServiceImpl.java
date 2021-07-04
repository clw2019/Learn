package com.clw.service.impl;

import com.clw.service.IFeignClientHystrixService;
import org.springframework.stereotype.Component;

/**
 * @Author: clw
 * @Description:
 * @Date: 2021/7/4 16:50
 */
@Component
public class HystrixFallbackServiceImpl implements IFeignClientHystrixService {

    @Override
    public String getOk(String id) {
        return "fallback method getOk";
    }

    @Override
    public String getTimeout(String id) {
        return "fallback method getTimeout";
    }
}
