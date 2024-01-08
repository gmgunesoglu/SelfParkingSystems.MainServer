package com.SelfParkingSystems.MainServer.repository;

import com.SelfParkingSystems.MainServer.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
