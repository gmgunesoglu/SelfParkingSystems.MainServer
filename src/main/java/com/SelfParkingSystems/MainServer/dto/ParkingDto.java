package com.SelfParkingSystems.MainServer.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ParkingDto {
    private String parkName;
    private String slotName;
    private String vehiclePlate;
    private String city;
    private String town;
    private String district;
    private Date date;
    private double hours2;
    private double hours4;
    private double hours6;
    private double hours10;
    private double hours24;
}
