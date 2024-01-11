package com.SelfParkingSystems.MainServer.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReservationerDto {
    private String name;
    private String phone;
    private String email;
    private Date date;

    public ReservationerDto() {
    }

    public ReservationerDto(String name, String phone, String email, Date date) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.date = date;
    }
}
