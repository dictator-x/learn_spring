package com.myspring.cloud.demo1.payment.dao;

import com.myspring.cloud.commons.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentDao extends JpaRepository<Payment, Long> {
}
