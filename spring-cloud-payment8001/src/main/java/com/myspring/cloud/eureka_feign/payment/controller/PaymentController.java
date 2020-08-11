package com.myspring.cloud.eureka_feign.payment.controller;

import com.myspring.cloud.commons.domain.Payment;
import com.myspring.cloud.commons.domain.CommonResult;
import com.myspring.cloud.eureka_feign.payment.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private DiscoveryClient discoveryClient;

    Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        Payment p = paymentService.create(payment);
        logger.info("*****insert result: " + (p != null ? p : payment));
        if ( p != null ) return new CommonResult(200, "insert success, serverPort: "+serverPort, p);
        return new CommonResult(444, "insert fail", null);
    }

    @GetMapping(value="/payment/get/{id}")
    public CommonResult create(@PathVariable("id") Long id) {
        Payment p = paymentService.getPaymentById(id);
        logger.info("*****Search result: " + (p != null ? p : id));
        if ( p != null ) return new CommonResult(200, "search success, serverPort: "+serverPort, p);
        return new CommonResult(444, "search fail", null);
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for ( String element : services ) {
            logger.info("*****element: " + element);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("SPRING-CLOUD-PAYMENT-SERVICE");
        for ( ServiceInstance instance : instances ) {
            logger.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }
        return discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB() {
        return serverPort;
    }
}
