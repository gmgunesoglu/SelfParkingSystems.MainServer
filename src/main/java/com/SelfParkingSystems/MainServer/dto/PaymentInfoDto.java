package com.SelfParkingSystems.MainServer.dto;

import lombok.Data;

@Data
public class PaymentInfoDto {
    private double amount;
    private String currency;
}