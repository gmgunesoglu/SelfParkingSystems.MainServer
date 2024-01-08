package com.SelfParkingSystems.MainServer.repository;

import com.SelfParkingSystems.MainServer.entity.Park;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkRepository extends JpaRepository<Park, Long> {
}
