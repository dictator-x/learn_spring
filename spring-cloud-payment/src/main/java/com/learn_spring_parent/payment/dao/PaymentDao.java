package com.learn_spring_parent.payment.dao;

import com.learn_spring_parent.payment.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentDao extends JpaRepository<Payment, Long> {
}
