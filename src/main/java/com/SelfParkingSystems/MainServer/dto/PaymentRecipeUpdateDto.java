package com.SelfParkingSystems.MainServer.dto;

import lombok.Data;

@Data
public class PaymentRecipeUpdateDto {
    private String name;
    private double hours2;
    private double hours4;
    private double hours6;
    private double hours10;
    private double hours24;
}