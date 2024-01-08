package com.SelfParkingSystems.MainServer.repository;

import com.SelfParkingSystems.MainServer.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
