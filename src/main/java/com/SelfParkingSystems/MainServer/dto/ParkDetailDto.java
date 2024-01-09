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
    private List<SlotDto> slots;
}
