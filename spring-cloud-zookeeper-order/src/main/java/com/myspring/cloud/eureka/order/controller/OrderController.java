package com.myspring.cloud.eureka.order.controller;

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
    private static final String PAYMENT_UTL = "http://spring-cloud-payment-service";
    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/order/zk")
    public String paymentInfo() {
        String result = restTemplate.getForObject(PAYMENT_UTL+"/payment/zk", String.class);
        return result;
    }

    @GetMapping(value="/order/create", produces = "application/json; charset=utf-8")
    public CommonResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_UTL+"/payment/create", payment, CommonResult.class);
    }

    @GetMapping(value="/order/get/{id}",produces = "application/json; charset=utf-8")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_UTL+"/payment/get/"+id, CommonResult.class);
    }

}
