package com.myspring.cloud.eureka.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EntityScan("com.myspring.cloud.commons.domain")
public class ConsulOrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsulOrderServiceApplication.class);
    }
}
