package com.SelfParkingSystems.MainServer.dto;

import lombok.Data;

@Data
public class ParkingInDto {
    private Long slotId;
    private String vehiclePlate;
}
