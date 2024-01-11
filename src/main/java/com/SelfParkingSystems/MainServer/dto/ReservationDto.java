package com.SelfParkingSystems.MainServer.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReservationDto {
    private Long id;
    private String parkName;
    private Date date;

    public ReservationDto() {
    }

    public ReservationDto(Long id, String parkName, Date date) {
        this.id = id;
        this.parkName = parkName;
        this.date = date;
    }
}
