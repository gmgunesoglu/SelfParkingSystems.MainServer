package com.SelfParkingSystems.MainServer.dto;

import lombok.Data;

import java.util.List;

@Data
public class SlotRegisterDto {
    private String parkName;
    private List<String> slotNames;
    private String paymentRecipeName;
}
