package com.myspring.cloud.demo1.order.controller;

import com.myspring.cloud.commons.domain.Payment;
import com.myspring.cloud.commons.domain.CommonResult;
import com.myspring.cloud.demo1.order.lb.MyLB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController
public class OrderController {
    private static final String PAYMENT_UTL = "http://SPRING-CLOUD-PAYMENT-SERVICE";
    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MyLB myLB;
    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping(value="/order/create", produces = "application/json; charset=utf-8")
    public CommonResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_UTL+"/payment/create", payment, CommonResult.class);
    }

    @GetMapping(value="/order/get/{id}",produces = "application/json; charset=utf-8")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_UTL+"/payment/get/"+id, CommonResult.class);
    }

    @GetMapping(value="/order/getentity/{id}",produces = "application/json; charset=utf-8")
    public CommonResult<Payment> getEntityPayment(@PathVariable("id") Long id) {
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_UTL+"/payment/get/"+id, CommonResult.class);
        if ( entity.getStatusCode().is2xxSuccessful() ) {
            return entity.getBody();
        } else {
            return new CommonResult<>(444, "query fails");
        }
    }

    @GetMapping(value = "/order/payment/lb")
    public String getPaymentLB() {
        List<ServiceInstance> instances = discoveryClient.getInstances("SPRING-CLOUD-PAYMENT-SERVICE");
        if ( instances == null || instances.size() < 1 ) {
            return null;
        }

        ServiceInstance serviceInstance = myLB.instance(instances);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri+"/payment/lb", String.class);
    }
}
