package com.myspring.cloud.stream_rabbitmq.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class StreamRabbitmqProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(StreamRabbitmqProviderApplication.class);
    }
}
