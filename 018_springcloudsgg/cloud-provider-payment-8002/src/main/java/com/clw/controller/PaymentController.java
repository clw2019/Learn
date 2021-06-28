package com.clw.controller;;

import com.clw.common.CommonResult;
import com.clw.entity.Payment;
import com.clw.service.IPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String port;

    @Resource
    private IPaymentService paymentService;

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
}
