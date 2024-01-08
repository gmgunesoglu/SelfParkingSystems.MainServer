package com.SelfParkingSystems.MainServer.repository;

import com.SelfParkingSystems.MainServer.entity.PaymentRecipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRecipeRepository extends JpaRepository<PaymentRecipe, Long> {
}
