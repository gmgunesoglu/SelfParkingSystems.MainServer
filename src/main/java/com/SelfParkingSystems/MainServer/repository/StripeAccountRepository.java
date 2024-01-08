package com.SelfParkingSystems.MainServer.repository;

import com.SelfParkingSystems.MainServer.entity.StripeAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StripeAccountRepository extends JpaRepository<StripeAccount, Long> {
}
