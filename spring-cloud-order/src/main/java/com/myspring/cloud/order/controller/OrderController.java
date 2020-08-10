package com.myspring.cloud.order.controller;

import com.myspring.cloud.commons.domain.Payment;
import com.myspring.cloud.commons.domain.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {
    private static final String PAYMENT_UTL = "http://localhost:9001";
    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/order/create")
    public CommonResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_UTL+"/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/order/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_UTL+"/payment/get/"+id, CommonResult.class);
    }

}
