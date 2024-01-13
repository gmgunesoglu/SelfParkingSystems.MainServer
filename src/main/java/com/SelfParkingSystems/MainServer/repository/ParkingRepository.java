package com.SelfParkingSystems.MainServer.repository;

import com.SelfParkingSystems.MainServer.dto.ParkingDto;
import com.SelfParkingSystems.MainServer.entity.Park;
import com.SelfParkingSystems.MainServer.entity.Parking;
import com.SelfParkingSystems.MainServer.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ParkingRepository extends JpaRepository<Parking, Long> {

    List<Parking> getBySlotIdAndDateAfter(Long slotId, Date date);

    @Query("SELECT new com.SelfParkingSystems.MainServer.dto.ParkingDto" +
            "(park.name, s.name, p.vehiclePlate, park.location.city, park.location.town, park.location.district, " +
            "p.date, s.paymentRecipe.hours2, s.paymentRecipe.hours4, s.paymentRecipe.hours6, s.paymentRecipe.hours10, " +
            "s.paymentRecipe.hours24) " +
            "FROM Parking p " +
            "JOIN Slot s ON s.id = p.slotId " +
            "JOIN Park park ON park.id = s.parkId ")
    ParkingDto getParkingDtoById(Long id);

    @Query("SELECT p " +
            "FROM Parking p " +
            "WHERE p.vehiclePlate = :vehiclePlate " +
            "ORDER BY p.date DESC LIMIT 1")
    Parking findLastParkingByVehiclePlate(String vehiclePlate);

    @Query("SELECT pay " +
            "FROM Parking p " +
            "JOIN Payment pay ON pay.parking.id = p.id " +
            "WHERE p.id = :id")
    Payment getPaymentOfParkingByParkingId(Long id);
}
