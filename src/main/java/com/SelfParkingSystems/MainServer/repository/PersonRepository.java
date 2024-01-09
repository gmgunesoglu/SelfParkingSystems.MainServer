package com.SelfParkingSystems.MainServer.repository;

import com.SelfParkingSystems.MainServer.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByUserName(String userName);

//    @Query("SELECT new com.SelfParkingSystems.MainServer.entity.Person(p.) FROM " +
//            "Person p JOIN StaffOfOwner so ON p.id = so.staffId")
//    List<Person> getStaffsOfOwner(Long ownerId);
//
//    @Query("SELECT new com.SoftTech.SelfParkingLot_RestApi.dto.ParkingLotWithTListDTO(p.id,p.name,l.city,l.town,l.district,p.address) FROM " +
//            "ParkingLot p JOIN Location l ON p.locationId=l.id WHERE p.enable=true AND l.enable=true AND p.id = :parkingLotId")
//    ParkingLotWithTListDTO getParkingLotWithTListDTO(Long parkingLotId);
}
