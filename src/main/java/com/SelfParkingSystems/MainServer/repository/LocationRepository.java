package com.SelfParkingSystems.MainServer.repository;

import com.SelfParkingSystems.MainServer.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
