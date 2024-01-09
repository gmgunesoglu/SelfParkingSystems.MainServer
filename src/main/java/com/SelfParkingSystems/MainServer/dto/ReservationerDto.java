package com.SelfParkingSystems.MainServer.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReservationerDto {
    private String name;
    private String phone;
    private String email;
    private Date date;
}
