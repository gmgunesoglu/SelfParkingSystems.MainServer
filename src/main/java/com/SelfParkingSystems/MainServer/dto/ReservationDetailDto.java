package com.SelfParkingSystems.MainServer.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReservationDetailDto {
    private Long id;
    private String parkName;
    private String slotName;
    private String city;
    private String town;
    private String address;
    private String district;
    private Date startDate;
    private Date endDate;

    public ReservationDetailDto() {
    }

    public ReservationDetailDto(Long id, String parkName, String slotName, String city, String town, String address, String district, Date startDate) {
        this.id = id;
        this.parkName = parkName;
        this.slotName = slotName;
        this.city = city;
        this.town = town;
        this.address = address;
        this.district = district;
        this.startDate = startDate;
    }
}
