package com.clw.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;

import com.clw.common.CommonResult;
import com.clw.entity.Payment;
import com.clw.service.IPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

;

@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String port;

    @Resource
    private IPaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/list")
    public CommonResult list() {
        List<Payment> list = paymentService.list();
        if (list != null && !list.isEmpty()) {
            return new CommonResult(200, "查询成功 => port : "+ port, list);
        }
        return new CommonResult(400, "查询失败 => port : "+ port);
    }

    @GetMapping("/get/{id}")
    public CommonResult getById(@PathVariable String id) {
        Payment payment = paymentService.getById(id);
        if (payment == null) {
            return new CommonResult(400, "查询失败 => port : "+ port);
        }
        return new CommonResult(200, "查询成功 => port : "+ port, payment);
    }

    @PostMapping("/add")
    public CommonResult add(@RequestBody Payment payment) {
        boolean save = paymentService.save(payment);
        if (!save) {
            return new CommonResult(400, "插入失败 => port : "+ port);
        }
        return new CommonResult(200, "插入成功 => port : "+ port,save);
    }

    @GetMapping("/timeout")
    public String timeout() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {
            e.printStackTrace();
            return "timeout is fail, the port is => " + port;
        } finally {
        }
        return "timeout is success, the port is => " + port;
    }

    @GetMapping("/discovery")
    public DiscoveryClient discoveryClient() {
        List<String> services = discoveryClient.getServices();
        services.forEach(service -> {
            log.info("service => " + service);
        });

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PROVIDER-PAYMENT");
        instances.forEach(instance -> {
            log.info("instance => " + instance.getHost() + "\t" +
                    instance.getPort() + "\t" +
                    instance.getUri() + "\t" +
                    instance.getServiceId() );
        });
        return discoveryClient;
    }
}
