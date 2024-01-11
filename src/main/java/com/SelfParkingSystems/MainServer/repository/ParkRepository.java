package com.SelfParkingSystems.MainServer.repository;

import com.SelfParkingSystems.MainServer.dto.ParkDetailDto;
import com.SelfParkingSystems.MainServer.dto.ParkDto;
import com.SelfParkingSystems.MainServer.entity.Park;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ParkRepository extends JpaRepository<Park, Long> {
    Park findByOwnerIdAndName(Long id, String parkName);
}
