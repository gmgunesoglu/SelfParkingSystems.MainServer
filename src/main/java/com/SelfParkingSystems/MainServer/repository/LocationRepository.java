package com.SelfParkingSystems.MainServer.repository;

import com.SelfParkingSystems.MainServer.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query("SELECT l.city FROM Location l")
    List<String> getCities();

    @Query("SELECT l.town FROM Location l WHERE l.city = :city")
    List<String> getTowns(String city);

    @Query("SELECT l.district FROM Location l WHERE l.city = :city AND l.town = :town")
    List<String> getDistricts(String city, String town);

    @Query("SELECT l.id FROM Location l WHERE l.city = :city AND l.town = :town AND l.district = :district")
    Long getLocationId(String city, String town, String district);

    Optional<Location> findByCityAndTownAndDistrict(String city, String town, String district);
//    Location getLocationByCityAndTownAndDistrict();
}
