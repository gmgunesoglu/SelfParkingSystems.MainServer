package com.SelfParkingSystems.MainServer.repository;

import com.SelfParkingSystems.MainServer.entity.Park;
import com.SelfParkingSystems.MainServer.entity.Parking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ParkingRepository extends JpaRepository<Parking, Long> {

    List<Parking> getBySlotIdAndDateAfter(Long slotId, Date date);
}
