package com.myspring.oauth2server.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {
    private static final ObjectMapper mapper = new ObjectMapper();

    @GetMapping("/ping")
    public String ping() throws JsonProcessingException {
        Map<String, Object> result = new HashMap<>();
        result.put("msg", "Pong");
        result.put("timestamp", DateTime.now().toString());
        return mapper.writeValueAsString(result);
    }
}
