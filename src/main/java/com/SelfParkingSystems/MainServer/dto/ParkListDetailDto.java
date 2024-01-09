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
}
