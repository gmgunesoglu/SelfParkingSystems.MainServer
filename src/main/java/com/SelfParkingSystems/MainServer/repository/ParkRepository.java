package com.SelfParkingSystems.MainServer.repository;

import com.SelfParkingSystems.MainServer.dto.ParkDetailDto;
import com.SelfParkingSystems.MainServer.dto.ParkDto;
import com.SelfParkingSystems.MainServer.entity.Park;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ParkRepository extends JpaRepository<Park, Long> {
    Park findByOwnerIdAndName(Long id, String parkName);

    @Query("SELECT new com.SelfParkingSystems.MainServer.dto.ParkDto" +
            "(p.id, p.name, p.secretKey, p.baseUrl, p.location.city, p.location.town, p.location.district, p.address, p.reservationDuration) " +
            "FROM Park p " +
            "WHERE p.ownerId = :ownerId AND p.enable = true")
    List<ParkDto> getParkDtoListByOwnerId(Long ownerId);

    @Query("SELECT new com.SelfParkingSystems.MainServer.dto.ParkDetailDto" +
            "(p.id, p.name, p.secretKey, p.baseUrl, p.location.city, p.location.town, p.location.district, p.address, p.reservationDuration) " +
            "FROM Park p " +
            "WHERE  p.id = :id AND p.ownerId = :ownerId AND p.enable = true")
    ParkDetailDto getParkDetailDtoByIdAndOwnerId(Long id, Long ownerId);
}
