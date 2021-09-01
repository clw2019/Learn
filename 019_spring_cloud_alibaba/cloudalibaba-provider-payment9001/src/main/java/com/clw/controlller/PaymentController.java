package com.clw.controlller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: clw
 * @Description:
 * @Date: 2021/8/31 21:46
 */
@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/nacos/{id}")
    public String getPayment(@PathVariable("id") String id) {
        return "Nacos registry, serverPort: " + serverPort + ", id: " + id;
    }
}
