package com.clw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.clw.entity.Payment;
import com.clw.mapper.PaymentMapper;
import com.clw.service.IPaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements IPaymentService {
}
