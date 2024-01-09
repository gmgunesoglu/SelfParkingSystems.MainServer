package com.SelfParkingSystems.MainServer.service;

import com.SelfParkingSystems.MainServer.entity.Location;
import com.SelfParkingSystems.MainServer.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService{

    private final LocationRepository locationRepository;

    @Override
    public List<String> getCities() {
        return locationRepository.getCities();
    }

    @Override
    public List<String> getTowns(String city) {
        return locationRepository.getTowns(city);
    }

    @Override
    public List<String> getDistricts(String city, String town) {
        return locationRepository.getDistricts(city, town);
    }

    @Override
    public Long getLocationId(String city, String town, String district) {
        return locationRepository.getLocationId(city, town, district);
    }
}
