package com.SelfParkingSystems.MainServer.repository;

import com.SelfParkingSystems.MainServer.dto.*;
import com.SelfParkingSystems.MainServer.entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SlotRepository extends JpaRepository<Slot, Long> {


    @Query("SELECT new com.SelfParkingSystems.MainServer.dto.SlotDto" +
            "(s.id, p.name, s.name, s.paymentRecipe.name, s.state) FROM Slot s " +
            "JOIN Park p ON s.parkId = p.id " +
            "JOIN Person per ON p.ownerId = per.id " +
            "WHERE per.id = :ownerId")
    List<SlotDto> getSlotDtoListByOwnerId(Long ownerId);

    @Query("SELECT new com.SelfParkingSystems.MainServer.dto.SlotDetailDto" +
            "(s.id, p.name, s.name, s.paymentRecipe.name, s.state) FROM Slot s " +
            "JOIN Park p ON s.parkId = p.id " +
            "JOIN Person per ON p.ownerId = per.id " +
            "WHERE per.id = :ownerId AND s.id = :id")
    SlotDetailDto getSlotDetailDtoById(Long id, Long ownerId);

    @Query("SELECT new com.SelfParkingSystems.MainServer.dto.ReservationerDto" +
            "(p.firstName, p.phoneNumber, p.email, r.date) FROM Reservation r " +
            "JOIN Person p ON r.userId = p.id " +
            "WHERE r.slotId = :slotId " +
            "ORDER BY r.date DESC LIMIT 1")
    ReservationerDto getReservationerDto(Long slotId);

    @Query("SELECT new com.SelfParkingSystems.MainServer.dto.SlotDto" +
            "(s.id, p.name, s.name, s.paymentRecipe.name, s.state) FROM Slot s " +
            "JOIN Park p ON s.parkId =  p.id " +
            "WHERE s.parkId = :parkId AND s.name = :name")
    SlotDto getSlotDtoByParkIdAndName(Long parkId, String name);

    @Query("SELECT new com.SelfParkingSystems.MainServer.dto.SlotDto" +
            "(s.id, p.name, s.name, s.paymentRecipe.name, s.state) FROM Slot s " +
            "JOIN Park p ON s.parkId = p.id " +
            "JOIN Person per ON p.ownerId = per.id " +
            "WHERE per.id = :ownerId AND p.name = :parkName AND s.name = :slotName")
    SlotDto getSlotDtoByOwnerIdAndParkNameAndSlotName(Long ownerId, String parkName, String slotName);

    @Query("SELECT per.id " +
            "FROM Slot s " +
            "JOIN Park p ON s.parkId = p.id " +
            "JOIN Person per ON p.ownerId = per.id " +
            "WHERE s.id = :id")
    Long getOwnerIdById(Long id);

    @Query("SELECT new com.SelfParkingSystems.MainServer.dto.SlotDto" +
            "(s.id, p.name, s.name, s.paymentRecipe.name, s.state) FROM Slot s " +
            "JOIN Park p ON s.parkId = p.id " +
            "WHERE s.id = :id")
    SlotDto getSlotDtoById(Long id);

    @Query("SELECT new com.SelfParkingSystems.MainServer.dto.SlotDto" +
            "(s.id, p.name, s.name, s.paymentRecipe.name, s.state) " +
            "FROM Slot s " +
            "JOIN Park p ON s.parkId = p.id " +
            "JOIN Person per ON p.ownerId = per.id " +
            "WHERE per.id = :ownerId AND s.paymentRecipe.id = :paymentRecipeId AND s.enable = true")
    List<SlotDto> getSlotDtoListByOwnerIdAndPaymentRecipeId(Long ownerId, Long paymentRecipeId);

    @Query("SELECT new com.SelfParkingSystems.MainServer.dto.SlotListDto" +
            "(s.id, s.name, s.state) " +
            "FROM Slot s " +
            "JOIN Park p ON s.parkId = p.id " +
            "WHERE s.enable = true AND p.id = :parkId")
    List<SlotListDto> getSlotListDtoListByParkId(Long parkId);

    @Query("SELECT new com.SelfParkingSystems.MainServer.dto.SlotListDetailDto" +
            "(s.id, s.name, s.state, s.paymentRecipe.hours2, s.paymentRecipe.hours4, s.paymentRecipe.hours6, s.paymentRecipe.hours10, s.paymentRecipe.hours24) " +
            "FROM Slot s WHERE s.enable = true AND s.id = :slotId")
    SlotListDetailDto getSlotListDetailDtoById(Long slotId);
}
