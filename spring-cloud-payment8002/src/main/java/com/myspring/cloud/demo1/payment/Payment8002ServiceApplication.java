package com.myspring.cloud.demo1.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EntityScan("com.myspring.cloud.commons.domain")
public class Payment8002ServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(Payment8002ServiceApplication.class);
    }
}
