package com.SelfParkingSystems.MainServer.dto;

import lombok.Data;

@Data
public class SlotUpdateDto {
    private String parkName;
    private String slotName;
    private String paymentRecipeName;
}
