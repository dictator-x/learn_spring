package com.myspring.basic.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/ping")
    @Secured("ROLE_ADMIN")
    public String ping() {
        return "pong";
    }
}
