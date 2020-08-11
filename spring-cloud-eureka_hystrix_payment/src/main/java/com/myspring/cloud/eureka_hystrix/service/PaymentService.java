package com.myspring.cloud.eureka_hystrix.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String paymentInfoOk(Integer id) {
        return "Threadpool: " + Thread.currentThread().getName() + " paymentInfo_OK, id: " + id + "\t" + "O(n_n)O";
    }

    public String paymentInfoTimeout(Integer id) {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Threadpool: " + Thread.currentThread().getName() + " paymentInfo_TIMEOUT, id: " + id + "\t" + "O(n_n)O, use 3 seconds";
    }
}
