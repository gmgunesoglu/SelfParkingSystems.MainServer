package com.SelfParkingSystems.MainServer.dto;

import lombok.Data;

import java.util.List;

@Data
public class ParkDetailDto {
    private Long id;
    private String name;
    private String secretKey;
    private String baseUrl;
    private String city;
    private String town;
    private String district;
    private String address;
    int reservationDuration;
    private List<SlotDto> slots;

    public ParkDetailDto() {
    }

    public ParkDetailDto(Long id, String name, String secretKey, String baseUrl, String city, String town, String district, String address, int reservationDuration) {
        this.id = id;
        this.name = name;
        this.secretKey = secretKey;
        this.baseUrl = baseUrl;
        this.city = city;
        this.town = town;
        this.district = district;
        this.address = address;
        this.reservationDuration = reservationDuration;
    }
}
