package com.SelfParkingSystems.MainServer.dto;

import lombok.Data;

import java.util.List;

@Data
public class PaymentRecipeDetailDto {
    private Long id;
    private String name;
    private List<SlotDto> slots;
    private double hours2;
    private double hours4;
    private double hours6;
    private double hours10;
    private double hours24;
}
