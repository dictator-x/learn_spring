package com.myspring.alibaba.order.service;

import com.myspring.cloud.commons.domain.CommonResult;
import com.myspring.cloud.commons.domain.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService {
    @Override
    public String getPayment(Integer id) {
        return "feign getPayment fallback";
    }

    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult(444, "feign payment fail back");
    }
}
