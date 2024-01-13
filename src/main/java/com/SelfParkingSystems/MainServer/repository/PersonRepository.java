package com.SelfParkingSystems.MainServer.repository;

import com.SelfParkingSystems.MainServer.dto.StaffInfoDto;
import com.SelfParkingSystems.MainServer.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByUserName(String userName);

    @Query("SELECT new com.SelfParkingSystems.MainServer.dto.StaffInfoDto" +
            "(p.id, p.userName, p.firstName, p.lastName, p.phoneNumber, p.email) " +
            "FROM Person p " +
            "JOIN StaffOfOwner soo " +
            "WHERE soo.owner.id = :ownerId")
    List<StaffInfoDto> getStaffInfoDtoListByOwnerId(Long ownerId);
}
