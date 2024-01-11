package com.SelfParkingSystems.MainServer.dto;

import lombok.Data;

@Data
public class ParkUpdateDto {
    private String name;
    private String secretKey;
    private String baseUrl;
    private String city;
    private String town;
    private String district;
    private String address;
    private int reservationDuration;
}
