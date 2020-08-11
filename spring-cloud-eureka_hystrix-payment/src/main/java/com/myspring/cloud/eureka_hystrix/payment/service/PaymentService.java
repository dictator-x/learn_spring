package com.myspring.cloud.eureka_hystrix.payment.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

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

}
