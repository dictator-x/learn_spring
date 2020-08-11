package com.myspring.cloud.demo1.order.service;

import com.myspring.cloud.commons.domain.CommonResult;
import com.myspring.cloud.commons.domain.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "SPRING-CLOUD-PAYMENT-SERVICE")
public interface IPaymentFeignService {

    @GetMapping(value = "/payment/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    //TODO: Inplement a long wait controller in payment service before use below interface.
    @GetMapping(value = "/payment/feign/timeout")
    String paymentGeignTimeout();
}
