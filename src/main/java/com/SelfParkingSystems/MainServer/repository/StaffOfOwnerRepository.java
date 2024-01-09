package com.SelfParkingSystems.MainServer.repository;

import com.SelfParkingSystems.MainServer.entity.StaffOfOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffOfOwnerRepository extends JpaRepository<StaffOfOwner, Long> {

    StaffOfOwner findByStaffIdAndOwnerId(Long staffId, Long ownerId);
}
