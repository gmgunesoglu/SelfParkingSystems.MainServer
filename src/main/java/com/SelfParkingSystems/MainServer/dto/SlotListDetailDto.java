package com.SelfParkingSystems.MainServer.dto;

import lombok.Data;

@Data
public class SlotListDetailDto {
    private Long id;
    private String name;
    private String state;
    private double hours2;
    private double hours4;
    private double hours6;
    private double hours10;
    private double hours24;
}
