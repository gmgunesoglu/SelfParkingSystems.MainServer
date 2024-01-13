package com.SelfParkingSystems.MainServer.service;

import com.SelfParkingSystems.MainServer.entity.Location;

import java.util.List;

public interface LocationService {
    List<String> getCities();

    List<String> getTowns(String city);

    List<String> getDistricts(String city, String town);

    Long getLocationId(String city, String town, String district);

    Location saveLocationIfNotExists(Location location);
}
