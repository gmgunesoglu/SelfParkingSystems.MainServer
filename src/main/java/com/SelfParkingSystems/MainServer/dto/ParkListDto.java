package com.SelfParkingSystems.MainServer.dto;

import lombok.Data;

@Data
public class ParkListDto {
    private Long id;
    private String name;
    private String city;
    private String town;
    private String district;
}
