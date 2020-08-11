package com.myspring.cloud.consul.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EntityScan("com.myspring.cloud.commons.domain")
public class ConsulPaymentServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsulPaymentServiceApplication.class);
    }
}
