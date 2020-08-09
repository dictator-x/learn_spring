package com.learn_spring_parent.auth;

import com.learn_spring_parent.auth.config.RsaKeyProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
//@EnableConfigurationProperties(RsaKeyProperty.class)
public class AuthServerApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(AuthServerApplication.class, args);
    }
}
