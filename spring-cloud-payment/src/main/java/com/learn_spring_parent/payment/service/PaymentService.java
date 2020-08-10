package com.learn_spring_parent.payment.service;

import com.learn_spring_parent.payment.dao.PaymentDao;
import com.learn_spring_parent.payment.domain.Payment;
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
