package com.myspring.cloud.eureka_feign.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EntityScan("com.myspring.cloud.commons.domain")
//@RibbonClient(name = "SPRING-CLOUD-PAYMENT-SERVICE", configuration = MyRule.class)
@EnableFeignClients
public class FeignOrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(FeignOrderServiceApplication.class);
    }
}
