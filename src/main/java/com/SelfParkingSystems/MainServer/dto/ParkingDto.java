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

    public ParkingDto() {
    }

    public ParkingDto(String parkName, String slotName, String vehiclePlate, String city, String town, String district, Date date, double hours2, double hours4, double hours6, double hours10, double hours24) {
        this.parkName = parkName;
        this.slotName = slotName;
        this.vehiclePlate = vehiclePlate;
        this.city = city;
        this.town = town;
        this.district = district;
        this.date = date;
        this.hours2 = hours2;
        this.hours4 = hours4;
        this.hours6 = hours6;
        this.hours10 = hours10;
        this.hours24 = hours24;
    }
}
