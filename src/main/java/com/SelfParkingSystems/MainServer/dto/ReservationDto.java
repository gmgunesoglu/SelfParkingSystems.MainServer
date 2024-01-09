package com.SelfParkingSystems.MainServer.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReservationDto {
    private Long id;
    private String parkName;
    private Date date;
}
