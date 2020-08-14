package com.myspring.alibaba.payment.controller;

import com.myspring.cloud.commons.domain.CommonResult;
import com.myspring.cloud.commons.domain.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/payment/nacos/{id}")
    public String getPayment(@PathVariable("id") Integer id) {
        return String.format("nacos registry, serverPort: %s \t id %d", serverPort, id);
    }

    public static HashMap<Long, Payment> hashMap = new HashMap<>();
    static {
        hashMap.put(1L, new Payment(1L, "AAAAAAA"));
        hashMap.put(2L, new Payment(2L, "BBBBBBB"));
        hashMap.put(3L, new Payment(3L, "CCCCCCC"));
    }

    @GetMapping(value = "/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id) {
        Payment payment = hashMap.get(id);
        CommonResult<Payment> result = new CommonResult<>(200, "from inmemory, serverPort: " + serverPort, payment);
        return result;
    }
}
