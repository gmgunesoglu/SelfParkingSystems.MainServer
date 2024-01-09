package com.SelfParkingSystems.MainServer.dto;

import lombok.Data;

@Data
public class ParkingOutDto {

    private String vehiclePlate;
    private double amount;
    private String currency;
    private String paymentToken;
}
