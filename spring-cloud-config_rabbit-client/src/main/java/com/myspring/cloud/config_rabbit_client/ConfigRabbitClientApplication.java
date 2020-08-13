package com.myspring.cloud.config_rabbit_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ConfigRabbitClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigRabbitClientApplication.class);
    }
}
