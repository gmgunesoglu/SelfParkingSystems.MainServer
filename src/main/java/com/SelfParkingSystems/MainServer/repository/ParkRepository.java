package com.SelfParkingSystems.MainServer.repository;

import com.SelfParkingSystems.MainServer.dto.ParkDetailDto;
import com.SelfParkingSystems.MainServer.dto.ParkDto;
import com.SelfParkingSystems.MainServer.entity.Park;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ParkRepository extends JpaRepository<Park, Long> {

//    @Query("SELECT new com.SelfParkingSystems.MainServer.dto.ParkDto" +
//            "(p.id, p.name, p.secretKey, p.baseUrl, l.city, l.town, l.district, p.address) FROM " +
//            "Park p JOIN Location l ON p.locationId = l.id WHERE p.id = :ownerId")
//    List<ParkDto> getParkDtoListByOwnerId(Long ownerId);



}
