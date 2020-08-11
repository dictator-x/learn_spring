package com.myspring.cloud.consul.payment.controller;

import com.myspring.cloud.commons.domain.Payment;
import com.myspring.cloud.commons.domain.CommonResult;
import com.myspring.cloud.consul.payment.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping(value = "/payment/consul")
    public String paymentzk() {
        return "springcloud with consul: " + serverPort + "\t" + UUID.randomUUID().toString();
    }

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        Payment p = paymentService.create(payment);
        logger.info("*****insert result: " + (p != null ? p : payment));
        if ( p != null ) return new CommonResult(200, "insert success", p);
        return new CommonResult(444, "insert fail", null);
    }

    @GetMapping(value="/payment/get/{id}")
    public CommonResult create(@PathVariable("id") Long id) {
        Payment p = paymentService.getPaymentById(id);
        logger.info("*****Search result: " + (p != null ? p : id));
        if ( p != null ) return new CommonResult(200, "search success", p);
        return new CommonResult(444, "search fail", null);
    }
}