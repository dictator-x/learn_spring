package com.myspring.alibaba.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.myspring.alibaba.order.service.PaymentService;
import com.myspring.cloud.commons.domain.CommonResult;
import com.myspring.cloud.commons.domain.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircleBreakerController {
    public static final String SERVER_URL = "http://alibaba-nacos-payment-provider";

    @Autowired
    private RestTemplate restTemplate;


    @RequestMapping("/consumer/fallback/{id}")
    //@SentinelResource(value = "fallback")
   // @SentinelResource(value = "fallback", fallback = "handlerFallback")
    //@SentinelResource(value = "fallback", blockHandler = "blockHandler")
    @SentinelResource(value = "fallback", blockHandler = "blockHandler", fallback = "handlerFallback", exceptionsToIgnore = NullPointerException.class)
    public CommonResult<Payment> fallback(@PathVariable Long id) {
        CommonResult<Payment> result = restTemplate.getForObject(SERVER_URL+"/paymentSQL/"+id, CommonResult.class, id);

        if ( id == 4 ) {
            throw new IllegalArgumentException("Illegal Argument Exception");
        } else if ( result.getData() == null ) {
            throw new NullPointerException("no ID record");
        }
        return result;
    }

    public CommonResult handlerFallback(@PathVariable Long id, Throwable e) {
        Payment payment = new Payment(id, "null");
        return new CommonResult(444, "handleFallback: " + e.getMessage(), payment);
    }

    public CommonResult blockHandler(@PathVariable Long id, BlockException blockException) {
        Payment payment = new Payment(id, "null");
        return new CommonResult(445, "blockHandler sentinel, blockExceptino " + blockException.getMessage());
    }

    @Autowired
    private PaymentService paymentService;

    @GetMapping(value = "/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id) {
        return paymentService.paymentSQL(id);
    }

}
