package com.myspring.alibaba.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class PaymentProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaymentProviderApplication.class);
    }
}
