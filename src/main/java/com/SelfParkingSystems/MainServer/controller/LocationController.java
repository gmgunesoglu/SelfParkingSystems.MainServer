package com.SelfParkingSystems.MainServer.controller;

import com.SelfParkingSystems.MainServer.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @GetMapping
    public List<String> getCities(){
        return locationService.getCities();
    }

    @GetMapping("/{city}")
    public List<String> getTowns(@PathVariable String city){
        return locationService.getTowns(city);
    }

    @GetMapping("/{city}/{town}")
    public List<String> getDistricts(@PathVariable String city, @PathVariable String town){
        return locationService.getDistricts(city, town);
    }

    @GetMapping("/{city}/{town}/{district}")
    public Long getLocationId(@PathVariable String city, @PathVariable String town, @PathVariable String district){
        return locationService.getLocationId(city, town, district);
    }
}
