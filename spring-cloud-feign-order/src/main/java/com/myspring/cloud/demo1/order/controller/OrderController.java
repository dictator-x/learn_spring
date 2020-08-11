package com.myspring.cloud.demo1.order.controller;

import com.myspring.cloud.commons.domain.CommonResult;
import com.myspring.cloud.commons.domain.Payment;
import com.myspring.cloud.demo1.order.service.IPaymentFeignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private IPaymentFeignService paymentFeignService;

    @GetMapping(value = "/order/get/{id}", produces = "application/json; charset=utf-8")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping(value = "/order/payment/feign/timeout")
    public String paymentFeignTimeout() {
        return paymentFeignService.paymentGeignTimeout();
    }
}
