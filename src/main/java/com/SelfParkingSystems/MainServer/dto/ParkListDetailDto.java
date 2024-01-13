package com.SelfParkingSystems.MainServer.dto;

import lombok.Data;

import java.util.List;

@Data
public class ParkListDetailDto {
    private Long id;
    private String name;
    private String city;
    private String town;
    private String district;
    private String address;
    private List<SlotListDto> slots;

    public ParkListDetailDto() {
    }

    public ParkListDetailDto(Long id, String name, String city, String town, String district, String address) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.town = town;
        this.district = district;
        this.address = address;
    }
}
