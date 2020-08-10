package com.myspring.payment.dao;

import com.myspring.payment.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentDao extends JpaRepository<Payment, Long> {
}
