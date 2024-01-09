package com.SelfParkingSystems.MainServer.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService{

    @Override
    public List<String> getCities() {
        return null;
    }

    @Override
    public List<String> getTowns(String city) {
        return null;
    }

    @Override
    public List<String> getDistricts(String city, String town) {
        return null;
    }

    @Override
    public Long getLocationId(String city, String town, String district) {
        return null;
    }
}
