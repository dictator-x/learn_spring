package com.myspring.cloud.eureka_feign_hystrix.order.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "----paymentFallbackService, paymentInfo_OK";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "----paymentFallbackService, paymentInfo_Timeout";
    }
}
