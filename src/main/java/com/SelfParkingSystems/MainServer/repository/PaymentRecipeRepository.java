package com.SelfParkingSystems.MainServer.repository;

import com.SelfParkingSystems.MainServer.dto.PaymentRecipeDetailDto;
import com.SelfParkingSystems.MainServer.dto.PaymentRecipeDto;
import com.SelfParkingSystems.MainServer.entity.PaymentRecipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentRecipeRepository extends JpaRepository<PaymentRecipe, Long> {

    PaymentRecipe findByOwnerIdAndName(Long id, String parkName);

    @Query("SELECT new com.SelfParkingSystems.MainServer.dto.PaymentRecipeDto" +
            "(p.id, p.name, p.hours2, p.hours4, p.hours6, p.hours10, p.hours24) " +
            "FROM PaymentRecipe p " +
            "WHERE p.ownerId = :ownerId AND p.enable = true")
    List<PaymentRecipeDto> getPaymentRecipeDtoList(Long ownerId);

    @Query("SELECT new com.SelfParkingSystems.MainServer.dto.PaymentRecipeDto" +
            "(p.id, p.name, p.hours2, p.hours4, p.hours6, p.hours10, p.hours24) " +
            "FROM PaymentRecipe p " +
            "WHERE p.id = :id AND p.ownerId = :ownerId AND p.enable = true")
    PaymentRecipeDetailDto getPaymentRecipeDetailDto(Long ownerId, Long id);

    PaymentRecipe getByIdAndOwnerIdAndEnable(Long id, Long ownerId, boolean enable);
}
