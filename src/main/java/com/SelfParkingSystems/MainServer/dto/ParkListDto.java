package com.SelfParkingSystems.MainServer.dto;

import lombok.Data;

@Data
public class ParkListDto {
    private Long id;
    private String name;
    private String city;
    private String town;
    private String district;


    public ParkListDto() {
    }

    public ParkListDto(Long id, String name, String city, String town, String district) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.town = town;
        this.district = district;
    }
}
