package com.SelfParkingSystems.MainServer.dto;

import lombok.Data;

@Data
public class PaymentTokenDto {
    private String paymentToken;
    private double amount;
    private String currency;
}
