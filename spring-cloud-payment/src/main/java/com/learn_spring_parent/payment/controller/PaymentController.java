package com.learn_spring_parent.payment.controller;

import com.learn_spring_parent.payment.domain.CommonResult;
import com.learn_spring_parent.payment.domain.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.learn_spring_parent.payment.service.PaymentService;

@RestController
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    Logger logger = LoggerFactory.getLogger(PaymentController.class);

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
