package com.SelfParkingSystems.MainServer.repository;

import com.SelfParkingSystems.MainServer.entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SlotRepository extends JpaRepository<Slot, Long> {
}
