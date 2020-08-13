package com.myspring.cloud.stream_rabbitmq.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class StreamRabbitmqClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(StreamRabbitmqClientApplication.class);
    }
}
