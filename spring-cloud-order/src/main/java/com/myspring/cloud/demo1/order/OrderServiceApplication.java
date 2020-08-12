package com.myspring.cloud.demo1.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
@EntityScan("com.myspring.cloud.commons.domain")
//@RibbonClient(name = "SPRING-CLOUD-PAYMENT-SERVICE", configuration = MyRule.class)
public class OrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class);
    }
}
