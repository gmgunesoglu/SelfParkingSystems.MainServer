package com.SelfParkingSystems.MainServer.dto;

import lombok.Data;

@Data
public class SlotDto {
    private Long id;
    private String parkName;
    private String slotName;
    private String paymentRecipeName;
    private SlotState state;
}
