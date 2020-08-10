package com.myspring.payment.service;

import com.myspring.payment.dao.PaymentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private PaymentDao paymentDao;

    public Payment create(Payment payment) {
        try {
            return paymentDao.save(payment);
        } catch ( Exception e ) {
            return null;
        }
    }

    public Payment getPaymentById(Long id) {
        return paymentDao.findById(id).orElse(null);
    }
}
