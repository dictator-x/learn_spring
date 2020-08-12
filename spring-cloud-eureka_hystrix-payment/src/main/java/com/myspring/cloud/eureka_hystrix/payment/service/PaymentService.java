package com.myspring.cloud.eureka_hystrix.payment.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.cloud.commons.util.IdUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String paymentInfoOk(Integer id) {
        return "Threadpool: " + Thread.currentThread().getName() + " paymentInfo_OK, id: " + id + "\t" + "O(n_n)O";
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="3000")
    })
    public String paymentInfoTimeout(Integer id) {
        int timeout = 2;
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Threadpool: " + Thread.currentThread().getName() + " paymentInfo_TIMEOUT, id: " + id + "\t" + "O(n_n)O, use 5 seconds";
    }

    public String paymentInfo_TimeOutHandler(Integer id) {
        return "Threadpool: " + Thread.currentThread().getName() + " paymentInfo_TimeOutHandler System busy, id: " + id + "\t" + "O(n-n)O";

    }

    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if ( id < 0 ) {
            throw new RuntimeException("id should not be negative");
        }
        String serialNumber = IdUtils.getUnresolvedServiceId();
        return Thread.currentThread().getName() + "\t" + "call success, serial: " + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id) {
        return "paymentCircuitBreaker_fallback - id: " + id;
    }

}
