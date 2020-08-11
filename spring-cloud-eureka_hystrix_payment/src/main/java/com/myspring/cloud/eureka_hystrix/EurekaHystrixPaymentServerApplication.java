package com.myspring.cloud.eureka_hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EurekaHystrixPaymentServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaHystrixPaymentServerApplication.class);
    }
}
