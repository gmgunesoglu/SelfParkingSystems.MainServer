package com.SelfParkingSystems.MainServer.repository;

import com.SelfParkingSystems.MainServer.dto.ReservationDetailDto;
import com.SelfParkingSystems.MainServer.dto.ReservationDto;
import com.SelfParkingSystems.MainServer.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT new com.SelfParkingSystems.MainServer.dto.ReservationDto" +
            "(r.id, p.name, r.date) " +
            "FROM Reservation r " +
            "JOIN Slot s ON s.id = r.slotId " +
            "JOIN Park p ON p.id = s.parkId " +
            "WHERE r.userId = :userId " +
            "ORDER BY r.date DESC LIMIT 30")
    List<ReservationDto> getReservationDtoListByUserId(Long userId);

    @Query("SELECT new com.SelfParkingSystems.MainServer.dto.ReservationDetailDto" +
            "(r.id, p.name, s.name, p.location.city, p.location.town, p.address, p.location.district, r.date) " +
            "FROM Reservation r " +
            "JOIN Slot s ON s.id = r.slotId " +
            "JOIN Park p ON p.id = s.parkId " +
            "WHERE r.userId = :userId AND r.id = :id")
    ReservationDetailDto getReservationDetailDtoByUserId(Long userId, Long id);

    @Query("SELECT p.reservationDuration " +
            "FROM Reservation r " +
            "JOIN Slot s on s.id = r.slotId " +
            "JOIN Park p on p.id = s.parkId " +
            "WHERE r.id = :reservationId")
    int getReservationDurationByReservationId(Long reservationId);

    @Query("SELECT p.name " +
            "FROM Slot s " +
            "JOIN Park p on p.id = s.parkId " +
            "WHERE s.id = :slotId")
    String getParkNameBySlotId(Long slotId);
}
